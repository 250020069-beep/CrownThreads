package com.example.crowthreads.controlador.juego;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.HorizontalScrollView;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.example.crowthreads.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

public class SettingsActivity extends AppCompatActivity {

    private int selectedCharacterId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SharedPreferences prefs = getSharedPreferences("CrownThreadsPrefs", Context.MODE_PRIVATE);
        selectedCharacterId = prefs.getInt("SELECTED_CHARACTER", 1);

        HorizontalScrollView scrollCharacters = findViewById(R.id.horizontal_scroll_characters);
        MaterialButton btnLeft = findViewById(R.id.btn_scroll_left);
        MaterialButton btnRight = findViewById(R.id.btn_scroll_right);

        if (btnLeft != null && scrollCharacters != null) {
            btnLeft.setOnClickListener(v -> scrollCharacters.smoothScrollBy(-280, 0));
        }
        if (btnRight != null && scrollCharacters != null) {
            btnRight.setOnClickListener(v -> scrollCharacters.smoothScrollBy(280, 0));
        }

        MaterialCardView card1 = findViewById(R.id.card_character_1);
        MaterialCardView card2 = findViewById(R.id.card_character_2);
        MaterialCardView card3 = findViewById(R.id.card_character_3);
        MaterialCardView card4 = findViewById(R.id.card_character_4);
        MaterialCardView[] cards = {card1, card2, card3, card4};

        if (selectedCharacterId >= 1 && selectedCharacterId <= cards.length) {
            highlightCard(cards[selectedCharacterId - 1], cards);
        }

        for (int i = 0; i < cards.length; i++) {
            final int id = i + 1;
            if (cards[i] != null) {
                cards[i].setOnClickListener(v -> {
                    selectedCharacterId = id;
                    highlightCard(cards[id - 1], cards);
                    Toast.makeText(this, "Modelo " + id + " seleccionada", Toast.LENGTH_SHORT).show();
                });
            }
        }

        SwitchCompat switchSave = findViewById(R.id.switch_save_outfit);
        SwitchCompat switchSparkle = findViewById(R.id.switch_sparkle_effects);
        SeekBar seekMusic = findViewById(R.id.seekbar_music);
        MaterialButton btnSave = findViewById(R.id.btn_save_settings);

        if (switchSave != null) switchSave.setChecked(prefs.getBoolean("SAVE_OUTFIT", true));
        if (switchSparkle != null) switchSparkle.setChecked(prefs.getBoolean("SPARKLE_EFFECTS", true));
        if (seekMusic != null) seekMusic.setProgress(prefs.getInt("MUSIC_VOLUME", 75));

        if (btnSave != null) {
            btnSave.setOnClickListener(v -> {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("SELECTED_CHARACTER", selectedCharacterId);
                if (switchSave != null) editor.putBoolean("SAVE_OUTFIT", switchSave.isChecked());
                if (switchSparkle != null) editor.putBoolean("SPARKLE_EFFECTS", switchSparkle.isChecked());
                if (seekMusic != null) editor.putInt("MUSIC_VOLUME", seekMusic.getProgress());
                editor.apply();

                Toast.makeText(this, "¡Ajustes guardados! 🌸", Toast.LENGTH_SHORT).show();
                finish();
            });
        }
    }

    private void highlightCard(MaterialCardView selected, MaterialCardView[] allCards) {
        for (MaterialCardView card : allCards) {
            if (card != null) {
                card.setCardBackgroundColor(
                        card == selected ? Color.parseColor("#FAD2E1") : Color.parseColor("#FFFFFF")
                );
            }
        }
    }
}