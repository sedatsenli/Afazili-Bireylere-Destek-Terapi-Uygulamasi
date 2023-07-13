package com.emirer.myapplication;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.emirer.myapplication.Adapter.hastaAdapter;
import com.emirer.myapplication.Model.hastaModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class terapist_veri_izle extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<hastaModel> list = new ArrayList<>();
    private hastaAdapter hastaAdapter;
    private ProgressDialog progressDialog;

    Button deleteButton;
    Button aramaButton;
    EditText hastaAdiEditText;
    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terapist_veri_izle);

        recyclerView = findViewById(R.id.recyler_view);

        progressDialog = new ProgressDialog(terapist_veri_izle.this);
        progressDialog.setTitle("Yükleniyor");
        progressDialog.setMessage("Veriler Alınıyor...");
        hastaAdapter = new hastaAdapter(getApplicationContext(), list);

        //filtre
        aramaButton = findViewById(R.id.faramaButton);
        hastaAdiEditText = findViewById(R.id.fhastaAdi);

        aramaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hastaAdi = hastaAdiEditText.getText().toString().trim();

                // Hasta adına göre verileri getiren metodu çağırın
                hastaAdiFiltre(hastaAdi);
            }
        });


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(hastaAdapter);
        progressDialog.show();
        db.collection("hastaVerileri")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        list.clear();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                hastaModel hastaModel = new hastaModel(document.getString("hastaAdi"), document.getString("hastaSoyadi"), document.getString("hastaEmail"), document.getString("hastaEgzersiz"),
                                        document.getString("hastaTekrar"), document.getString("hastaSonuc"));
                                list.add(hastaModel);
                            }
                            hastaAdapter.notifyDataSetChanged();

                        } else {
                            Toast.makeText(getApplicationContext(), "Veri Alınamadı!", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }

    public void silButon(View view) { //isimsil yanlis olmus kullaniciyi komple siliyoruz

        db.collection("hastaVerileri")
                .limit(1)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful() && !task.getResult().isEmpty()) {
                            DocumentSnapshot document = task.getResult().getDocuments().get(0);
                            String documentId = document.getId();
                            document.getReference().delete()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(terapist_veri_izle.this, "Veri Başarıyla Silindi", Toast.LENGTH_SHORT).show();
                                                recreate();
                                            } else {
                                                Toast.makeText(terapist_veri_izle.this, "Veri Silme Hatası: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        } else {
                            Toast.makeText(terapist_veri_izle.this, "Veri Bulunamadı", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void hastaAdiFiltre(String hastaAdi) {
        CollectionReference hastaVerileriCollectionRef = db.collection("hastaVerileri");

        Query hastaAdiSorgusu = hastaVerileriCollectionRef.whereEqualTo("hastaAdi", hastaAdi);

        hastaAdiSorgusu.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                list.clear();
                if (task.isSuccessful()) {
                    QuerySnapshot querySnapshot = task.getResult();

                    for (DocumentSnapshot documentSnapshot : querySnapshot.getDocuments()) {

                        String hastaAdi = documentSnapshot.getString("hastaAdi");
                        String hastaSoyadi = documentSnapshot.getString("hastaSoyadi");
                        String hastaEmail = documentSnapshot.getString("hastaEmail");
                        String hastaEgzersiz = documentSnapshot.getString("hastaEgzersiz");
                        String hastaTekrar = documentSnapshot.getString("hastaTekrar");
                        String hastaSonuc = documentSnapshot.getString("hastaSonuc");


                        hastaModel hastaModel1 = new hastaModel(hastaAdi, hastaSoyadi, hastaEmail, hastaEgzersiz, hastaTekrar, hastaSonuc);
                        list.add(hastaModel1);
                        hastaAdapter.notifyDataSetChanged();
                    }


                } else {
                    Exception exception = task.getException();

                }
            }
        });
    }

}







