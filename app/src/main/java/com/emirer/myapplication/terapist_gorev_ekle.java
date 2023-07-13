package com.emirer.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.emirer.myapplication.Model.hastaModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;

public class terapist_gorev_ekle extends AppCompatActivity {

    private EditText hastaEgzersiz, hastaTekrar, hastaAdi;

    private Button egzersizEkle;
    private List<hastaModel> list;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    //private DocumentReference documentRef = db.collection("hastaVerileri").document("ieAY0ldCNWJQXIawAjCl");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terapist_gorev_ekle);

        hastaEgzersiz = findViewById(R.id.editEgzersiz);
        hastaTekrar = findViewById(R.id.editTekrar);
        hastaAdi = findViewById(R.id.editHastaadi);

        egzersizEkle = findViewById(R.id.egzersizEkle);

        egzersizEkle.setOnClickListener(v -> {
            if (hastaEgzersiz.getText().length()>0 && hastaTekrar.getText().length() > 0 && hastaAdi.getText().length() > 0){
                veriEkle(hastaEgzersiz.getText().toString(), hastaTekrar.getText().toString() , hastaAdi.getText().toString());
            }
            else {
                Toast.makeText(getApplicationContext(), "Lutfen tum alanlari doldurunuz!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @SuppressLint("WrongViewCast")
    private CompletableFuture<DocumentReference> getDocumentReferansi(String hastaAdi) {
        CompletableFuture<DocumentReference> future = new CompletableFuture<>();
        CollectionReference hastaVerileriCollectionRef = db.collection("hastaVerileri");
        Query hastaAdiSorgu = hastaVerileriCollectionRef.whereEqualTo("hastaAdi", hastaAdi);

        hastaAdiSorgu.limit(1).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    QuerySnapshot querySnapshot = task.getResult();
                    if (!querySnapshot.isEmpty()) {
                        DocumentSnapshot documentSnapshot = querySnapshot.getDocuments().get(0);
                        String docId = documentSnapshot.getId();
                        DocumentReference docRef = hastaVerileriCollectionRef.document(docId);
                        future.complete(docRef);
                    } else {
                        DocumentReference yeniDocRef = hastaVerileriCollectionRef.document();
                        future.complete(yeniDocRef);
                    }
                } else {
                    Exception exception = task.getException();
                    future.completeExceptionally(exception);
                }
            }
        });

        return future;
    }
    private void veriEkle(String hastaAdi, String hastaEgzersiz, String hastaTekrar) {
        EditText editTextHastaAdi = findViewById(R.id.editHastaadi);
        hastaAdi = editTextHastaAdi.getText().toString();
        EditText editTextHastaEgzersiz = findViewById(R.id.editEgzersiz);
        hastaEgzersiz = editTextHastaEgzersiz.getText().toString();
        EditText editTextHastaTekrar = findViewById(R.id.editTekrar);
        hastaTekrar = editTextHastaTekrar.getText().toString();

        CompletableFuture<DocumentReference> belgeRefFuture = getDocumentReferansi(hastaAdi);

        String finalHastaEgzersiz = hastaEgzersiz;
        String finalHastaTekrar = hastaTekrar;
        belgeRefFuture.thenAccept(new Consumer<DocumentReference>() {
            @Override
            public void accept(DocumentReference belgeRef) {
                Map<String, Object> hasta = new HashMap<>();
                hasta.put("hastaEgzersiz", finalHastaEgzersiz);
                hasta.put("hastaTekrar", finalHastaTekrar);

                belgeRef.update(hasta)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(), "Veri Ekleme Başarılı", Toast.LENGTH_SHORT).show();
                                finish();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Hasta kaydı bulunamadı!", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        }).exceptionally(new Function<Throwable, Void>() {
            @Override
            public Void apply(Throwable throwable) {
                Toast.makeText(getApplicationContext(), throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                return null;
            }
        });
    }
}