package com.emirer.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Terapist_Giris extends AppCompatActivity {

    private TextView editEmail;
    private TextView editSifre;
    private String txtEmail, txtSifre;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    Button kullaniciarayuz;

    Button kayitEkrani;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terapist_giris);

        editEmail = (EditText) findViewById(R.id.emailGiris);
        editSifre = (EditText) findViewById(R.id.sifreGiris);

        mAuth = FirebaseAuth.getInstance();


        kayitEkrani = (Button)findViewById(R.id.kayitEkrani);
        kayitEkrani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kayitActivity = new Intent(getApplicationContext(),Terapist_Kayit.class);
                startActivity(kayitActivity);
            }
        });

    }


    public void girisButonu (View view) {
        txtEmail = editEmail.getText().toString();
        txtSifre = editSifre.getText().toString();
        if(!TextUtils.isEmpty(txtEmail) && !TextUtils.isEmpty(txtSifre)) {

            mAuth.signInWithEmailAndPassword(txtEmail, txtSifre)
                    .addOnSuccessListener(this, new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            mUser = mAuth.getCurrentUser();

                            kullaniciarayuz = (Button)findViewById(R.id.girisButonu);
                            kullaniciarayuz.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent KullaniciArayuzSecimi = new Intent(getApplicationContext(),terapist_ana_ekran.class);
                                    startActivity(KullaniciArayuzSecimi);
                                }
                            });
                            // System.out.println("Kullanici Adi: " + mUser.getDisplayName());


                        }
                    }).addOnFailureListener(this, new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Terapist_Giris.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
        }
        else {
            Toast.makeText(this, "Email ve Sifre Bos Birakilamaz", Toast.LENGTH_SHORT).show();
        }
    }
}