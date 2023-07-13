package com.emirer.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class terapist_ana_ekran extends AppCompatActivity {

    ImageButton gorevekle;
    ImageButton hastaekle;
    ImageButton veriizle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terapist_ana_ekran);

        hastaekle = findViewById(R.id.imageButton);
        gorevekle = findViewById(R.id.imageButton2);
        veriizle = findViewById(R.id.imageButton3);

        hastaekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hastaekle1 = new Intent(getApplicationContext(),terapist_hasta_ekle.class);
                startActivity(hastaekle1);
            }
        });

        gorevekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gorevekle1 = new Intent(getApplicationContext(),terapist_gorev_ekle.class);
                startActivity(gorevekle1);
            }
        });

        veriizle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent veriizle1 = new Intent(getApplicationContext(),terapist_veri_izle.class);
                startActivity(veriizle1);
            }
        });
    }
}