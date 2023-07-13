package com.emirer.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class terapist_hasta_ekle extends AppCompatActivity {

    private EditText hastaAdi, hastaSoyadi, hastaEmail;

    private Button hastaKayit;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terapist_hasta_ekle);

        hastaAdi = findViewById(R.id.hastaAdi);
        hastaSoyadi = findViewById(R.id.hastaSoyadi);
        hastaEmail = findViewById(R.id.hastaEmail);

        hastaKayit = findViewById(R.id.hastakaydet);

        hastaKayit.setOnClickListener(v -> {
            if (hastaAdi.getText().length()>0 && hastaSoyadi.getText().length() > 0 && hastaEmail.getText().length()>0){
                saveData(hastaAdi.getText().toString(), hastaSoyadi.getText().toString(), hastaEmail.getText().toString());
            }
            else {
                Toast.makeText(getApplicationContext(), "Lutfen tum alanlari doldurunuz!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void saveData(String hastaAdi, String hastaSoyadi, String hastaEmail) {
        Map<String, Object> hasta = new HashMap<>();
        hasta.put("hastaAdi", hastaAdi);
        hasta.put("hastaSoyadi", hastaSoyadi);
        hasta.put("hastaEmail", hastaEmail);

       db.collection("hastaVerileri")
                .add(hasta)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(), "Veri Girisi Basarili", Toast.LENGTH_SHORT).show();
                        finish();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(terapist_hasta_ekle.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}