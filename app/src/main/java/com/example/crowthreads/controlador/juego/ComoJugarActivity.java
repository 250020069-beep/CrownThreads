package com.example.crowthreads.controlador.juego;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crowthreads.R;
import com.google.android.material.button.MaterialButton;

public class ComoJugarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viata_como_se_juega);

        MaterialButton btnVolver = findViewById(R.id.btn_volver_como_jugar);
        if (btnVolver != null) {
            btnVolver.setOnClickListener(v -> finish());
        }
    }
}