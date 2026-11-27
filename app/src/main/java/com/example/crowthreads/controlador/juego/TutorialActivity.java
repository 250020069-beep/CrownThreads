package com.example.crowthreads.controlador.juego;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crowthreads.R;
import com.google.android.material.button.MaterialButton;

public class TutorialActivity extends AppCompatActivity {

    private int currentStep = 0;
    private final String[] stepIcons = {"👑", "👗", "💇‍♀️", "📸"};
    private final String[] stepColors = {"#FCEBF1", "#E8DFF5", "#FCE1E4", "#FFF0F5"};
    private final String[] stepTitles = {
            "1. Bienvenida al Palacio",
            "2. Categorías de Vestuario",
            "3. Peinados y Accesorios",
            "4. Guarda tus Creaciones"
    };
    private final String[] stepDescriptions = {
            "En Crown & Threads tienes total libertad creativa. No hay niveles ni límites de tiempo: diseña atuendos a tu propio ritmo.",
            "Usa el menú lateral izquierdo del vestidor para elegir entre vestidos, zapatillas, sombreros y aretes.",
            "Toca las opciones de la barra inferior para probar distintos peinados y combinaciones sobre tu modelo al instante.",
            "Cuando termines tu diseño favorito, guárdalo en la Galería o ajusta tus preferencias en los Ajustes del Palacio."
    };

    private TextView tvStepIndicator;
    private TextView tvIcon;
    private TextView tvTitle;
    private TextView tvDescription;
    private ProgressBar progressBar;
    private MaterialButton btnPrev;
    private MaterialButton btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        tvStepIndicator = findViewById(R.id.tv_tutorial_step_indicator);
        tvIcon = findViewById(R.id.tv_tutorial_icon);
        tvTitle = findViewById(R.id.tv_tutorial_title);
        tvDescription = findViewById(R.id.tv_tutorial_description);
        progressBar = findViewById(R.id.progress_tutorial);
        btnPrev = findViewById(R.id.btn_prev_step);
        btnNext = findViewById(R.id.btn_next_step);
        MaterialButton btnSkip = findViewById(R.id.btn_skip_tutorial);

        updateTutorialUI();

        if (btnSkip != null) {
            btnSkip.setOnClickListener(v -> finish());
        }

        if (btnPrev != null) {
            btnPrev.setOnClickListener(v -> {
                if (currentStep > 0) {
                    currentStep--;
                    updateTutorialUI();
                }
            });
        }

        if (btnNext != null) {
            btnNext.setOnClickListener(v -> {
                if (currentStep < stepTitles.length - 1) {
                    currentStep++;
                    updateTutorialUI();
                } else {
                    finish();
                }
            });
        }
    }

    private void updateTutorialUI() {
        int total = stepTitles.length;
        if (tvStepIndicator != null) tvStepIndicator.setText("Paso " + (currentStep + 1) + " de " + total);
        if (progressBar != null) progressBar.setProgress(currentStep + 1);
        if (tvIcon != null) {
            tvIcon.setText(stepIcons[currentStep]);
            tvIcon.setBackgroundColor(Color.parseColor(stepColors[currentStep]));
        }
        if (tvTitle != null) tvTitle.setText(stepTitles[currentStep]);
        if (tvDescription != null) tvDescription.setText(stepDescriptions[currentStep]);

        if (btnPrev != null) {
            btnPrev.setEnabled(currentStep > 0);
            btnPrev.setAlpha(currentStep > 0 ? 1.0f : 0.5f);
        }
        if (btnNext != null) {
            btnNext.setText(currentStep == total - 1 ? "¡COMENZAR!" : "SIGUIENTE");
        }
    }
}