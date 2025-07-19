package com.example.projetquizesgi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Set;

public class ResultatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);

        int score = getIntent().getIntExtra("SCORE", 0);
        long chrono = getIntent().getLongExtra("CHRONO", 0);
        String userName = getIntent().getStringExtra("USER_NAME");

        TextView scoreTextView = findViewById(R.id.scoreTextView);
        TextView messageTextView = findViewById(R.id.messageTextView);
        TextView playerNameTextView = findViewById(R.id.playerNameTextView); // À ajouter dans layout

        String formattedTime = String.format("%02d:%02d", (chrono / 60000), (chrono / 1000) % 60);

        playerNameTextView.setText("Nom : " + userName);
        scoreTextView.setText("Votre score est : " + score);
        messageTextView.setText("Temps : " + formattedTime);

        // Enregistrement dans SharedPreferences
        SharedPreferences prefs = getSharedPreferences("quiz", MODE_PRIVATE);
        Set<String> scores = prefs.getStringSet("scores", new HashSet<>());
        scores.add(userName + " : " + score + " pts - " + formattedTime);
        prefs.edit().putStringSet("scores", scores).apply();

        Button replay = findViewById(R.id.retryButton);
        Button home = findViewById(R.id.restartButton);

        replay.setOnClickListener(v -> {
            Intent intent = new Intent(ResultatActivity.this, QuizActivity.class);
            intent.putExtra("USER_NAME", userName);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        home.setOnClickListener(v -> {
            Intent intent = new Intent(ResultatActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}
