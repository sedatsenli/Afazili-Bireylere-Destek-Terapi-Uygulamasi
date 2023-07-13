package com.emirer.myapplication;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Terapist_Kayit extends AppCompatActivity {

    private TextView editEmail;
    private TextView editSifre;
    private String txtEmail, txtSifre;
    private FirebaseAuth mAuth;

    Button kayit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terapist_kayit);

        editEmail = (TextView) findViewById(R.id.emailKayit);
        editSifre = (TextView) findViewById(R.id.sifreKayit);

        kayit= findViewById(R.id.kayitButonu);

        mAuth = FirebaseAuth.getInstance();

    }

    public void kayitButonu(View v) {
        txtEmail = editEmail.getText().toString();
        txtSifre = editSifre.getText().toString();
        if (!TextUtils.isEmpty(txtEmail) && !TextUtils.isEmpty(txtSifre)) {

            mAuth.createUserWithEmailAndPassword(txtEmail, txtSifre)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful() ) {
                                Toast.makeText(Terapist_Kayit.this, "Kayit Islemi Basarili", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(Terapist_Kayit.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }
        else {
            Toast.makeText(this, "Email, Şifre ve KVKK Bos Geçilemez.", Toast.LENGTH_SHORT).show();
        }

    }
}