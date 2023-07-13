package com.emirer.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ekran_secimi extends AppCompatActivity {

    Button kullanicigiris;
    Button terapistgiris;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekran_secimi);

        kullanicigiris = findViewById(R.id.kullanicigrs);
        terapistgiris = findViewById(R.id.terapistgrs);

        kullanicigiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Kekransecimi = new Intent(getApplicationContext(),kullanici_ekran_secimi.class);
                startActivity(Kekransecimi);
            }
        });

        terapistgiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent terapistgiris = new Intent(getApplicationContext(),Terapist_Giris.class);
                startActivity(terapistgiris);
            }
        });


    }
}