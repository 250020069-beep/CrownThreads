package com.example.crowthreads;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crowthreads.controlador.juego.ComoJugarActivity;
import com.example.crowthreads.controlador.juego.GaleriaActivity;
import com.example.crowthreads.controlador.juego.SettingsActivity;
import com.example.crowthreads.controlador.juego.TutorialActivity;
import com.example.crowthreads.modelo.BD.CrownThreadsDBHelper;
import com.example.crowthreads.modelo.entity.Atuendo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Si ya tienes vista_inicio.xml en res/layout, descomenta estas líneas:
        /*
        setContentView(R.layout.vista_inicio);

        Button btnAjustes = findViewById(R.id.btnAjustes);
        Button btnComoJugar = findViewById(R.id.btnComoJugar);
        Button btnGaleria = findViewById(R.id.btnGaleria);

        if (btnAjustes != null) {
            btnAjustes.setOnClickListener(v -> startActivity(new Intent(this, SettingsActivity.class)));
        }
        if (btnComoJugar != null) {
            btnComoJugar.setOnClickListener(v -> startActivity(new Intent(this, ComoJugarActivity.class)));
        }
        if (btnGaleria != null) {
            btnGaleria.setOnClickListener(v -> startActivity(new Intent(this, GaleriaActivity.class)));
        }
        */

        // --- PRUEBA DIRECTA DE BASE DE DATOS Y GALERÍA ---
        // Inserta un diseño de ejemplo si la BD está vacía y abre GaleriaActivity
        CrownThreadsDBHelper db = new CrownThreadsDBHelper(this);
        if (db.obtenerTodosLosAtuendos().isEmpty()) {
            db.guardarAtuendo(new Atuendo(0, "Gala Sakura", "Meredith", 1, 2, 1, 3, 2));
        }

        startActivity(new Intent(this, GaleriaActivity.class));
        finish();
    }
}