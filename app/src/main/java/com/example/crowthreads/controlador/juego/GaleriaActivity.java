package com.example.crowthreads.controlador.juego;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crowthreads.R;
import com.example.crowthreads.modelo.BD.CrownThreadsDBHelper;
import com.example.crowthreads.modelo.entity.Atuendo;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class GaleriaActivity extends AppCompatActivity {

    private CrownThreadsDBHelper dbHelper;
    private LinearLayout contenedorLista;
    private MaterialCardView cardVacia;
    private TextView tvContador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_galeria);

        dbHelper = new CrownThreadsDBHelper(this);
        contenedorLista = findViewById(R.id.contenedor_lista_galeria);
        cardVacia = findViewById(R.id.card_galeria_vacia);
        tvContador = findViewById(R.id.tv_galeria_contador);

        MaterialButton btnVolver = findViewById(R.id.btn_volver_galeria);
        if (btnVolver != null) {
            btnVolver.setOnClickListener(v -> finish());
        }

        cargarAtuendosDesdeBD();
    }

    private void cargarAtuendosDesdeBD() {
        if (contenedorLista == null) return;
        contenedorLista.removeAllViews();
        List<Atuendo> listaAtuendos = dbHelper.obtenerTodosLosAtuendos();

        if (listaAtuendos.isEmpty()) {
            if (cardVacia != null) cardVacia.setVisibility(View.VISIBLE);
            if (tvContador != null) tvContador.setText("0 diseños guardados en el palacio");
            return;
        }

        if (cardVacia != null) cardVacia.setVisibility(View.GONE);
        int total = listaAtuendos.size();
        if (tvContador != null) {
            tvContador.setText(total + (total == 1 ? " diseño guardado" : " diseños guardados"));
        }

        for (Atuendo atuendo : listaAtuendos) {
            agregarTarjetaAtuendo(atuendo);
        }
    }

    private void agregarTarjetaAtuendo(Atuendo atuendo) {
        MaterialCardView card = new MaterialCardView(this);
        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        cardParams.setMargins(0, 0, 0, dpToPx(12));
        card.setLayoutParams(cardParams);
        card.setRadius(dpToPx(18));
        card.setCardElevation(dpToPx(2));
        card.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
        card.setStrokeColor(Color.parseColor("#FAD2E1"));
        card.setStrokeWidth(dpToPx(1));

        LinearLayout fila = new LinearLayout(this);
        fila.setOrientation(LinearLayout.HORIZONTAL);
        fila.setPadding(dpToPx(14), dpToPx(14), dpToPx(14), dpToPx(14));
        fila.setGravity(Gravity.CENTER_VERTICAL);

        TextView icono = new TextView(this);
        LinearLayout.LayoutParams iconParams = new LinearLayout.LayoutParams(dpToPx(46), dpToPx(46));
        iconParams.setMargins(0, 0, dpToPx(12), 0);
        icono.setLayoutParams(iconParams);
        icono.setText("👑");
        icono.setTextSize(20);
        icono.setGravity(Gravity.CENTER);
        icono.setBackgroundColor(Color.parseColor("#FCEBF1"));

        LinearLayout infoColumna = new LinearLayout(this);
        infoColumna.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
        infoColumna.setOrientation(LinearLayout.VERTICAL);

        TextView tvTitulo = new TextView(this);
        tvTitulo.setText(atuendo.getNombreDiseno() + " (" + atuendo.getPersonaje() + ")");
        tvTitulo.setTextSize(15);
        tvTitulo.setTypeface(null, Typeface.BOLD);
        tvTitulo.setTextColor(Color.parseColor("#5E3A47"));

        TextView tvDetalle = new TextView(this);
        tvDetalle.setText("👗 #" + atuendo.getVestidoId() + "  |  👠 #" + atuendo.getZapatillasId() +
                "  |  👒 #" + atuendo.getSombreroId() + "  |  ✨ #" + atuendo.getAretesId() +
                "  |  💇‍♀️ #" + atuendo.getPeloId());
        tvDetalle.setTextSize(11);
        tvDetalle.setTextColor(Color.parseColor("#8C5C6F"));
        tvDetalle.setPadding(0, dpToPx(4), 0, 0);

        infoColumna.addView(tvTitulo);
        infoColumna.addView(tvDetalle);

        MaterialButton btnEliminar = new MaterialButton(this);
        btnEliminar.setLayoutParams(new LinearLayout.LayoutParams(dpToPx(48), dpToPx(48)));
        btnEliminar.setInsetTop(0);
        btnEliminar.setInsetBottom(0);
        btnEliminar.setPadding(0, 0, 0, 0);
        btnEliminar.setText("🗑️");
        btnEliminar.setTextSize(16);
        btnEliminar.setCornerRadius(dpToPx(24));
        btnEliminar.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FCE1E4")));

        btnEliminar.setOnClickListener(v -> {
            dbHelper.eliminarAtuendo(atuendo.getId());
            Toast.makeText(this, "Diseño eliminado", Toast.LENGTH_SHORT).show();
            cargarAtuendosDesdeBD();
        });

        fila.addView(icono);
        fila.addView(infoColumna);
        fila.addView(btnEliminar);
        card.addView(fila);

        contenedorLista.addView(card);
    }

    private int dpToPx(int dp) {
        return Math.round(dp * getResources().getDisplayMetrics().density);
    }
}