package com.emirer.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class hastane_destek extends AppCompatActivity {
    Button susadimB;
    Button aciktimB;

    Button ihtiyac;
    Button aileara;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hastane_destek);

        susadimB = findViewById(R.id.susadim);
        aciktimB = findViewById(R.id.aciktim);
        ihtiyac = findViewById(R.id.tuvaletIhtiyaci);
        aileara = findViewById(R.id.aileArama);

        susadimB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(hastane_destek.this, "Susadim", Toast.LENGTH_SHORT).show();
            }
        });

        aciktimB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(hastane_destek.this, "Acıktım", Toast.LENGTH_SHORT).show();
            }
        });

        ihtiyac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(hastane_destek.this, "Tuvalet İhtiyacı", Toast.LENGTH_SHORT).show();
            }
        });

        aileara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(hastane_destek.this, "Ailemi Ara", Toast.LENGTH_SHORT).show();
            }
        });



    }
}