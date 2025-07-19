package com.example.projetquizesgi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProfilActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        // Appliquer la couleur de fond définie dans colors.xml
        findViewById(R.id.profilRootLayout)
                .setBackgroundResource(R.color.profilBackground);

        // Extraire le prénom passé par Intent
        String firstName = getIntent().getStringExtra("FIRST_NAME");
        if (firstName == null) firstName = "";

        // Chaîne multilingue avec placeholder
        TextView welcomeTextView = findViewById(R.id.welcomeTextView);
        welcomeTextView.setText(getString(R.string.welcome_message, firstName));

        // Image de profil (depuis drawable)
        ImageView imageView = findViewById(R.id.profileImageView);
        imageView.setImageResource(R.drawable.baseline_account_circle_24);

        // Bouton retour
        Button retourBtn = findViewById(R.id.backButton);
        retourBtn.setOnClickListener(v -> finish());
    }
}
