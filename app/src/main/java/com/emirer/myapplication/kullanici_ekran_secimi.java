package com.emirer.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class kullanici_ekran_secimi extends AppCompatActivity {

    Button hastanedestekgiris;

    Button bireyselcalismagiris;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kullanici_ekran_secimi);

        hastanedestekgiris = findViewById(R.id.hastanedestekgrs);

        hastanedestekgiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hastanedestekgiris1 = new Intent(getApplicationContext(),hastane_destek.class);
                startActivity(hastanedestekgiris1);
            }
        });
    }
}