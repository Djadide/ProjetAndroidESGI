package com.example.projetquizesgi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.projetquizesgi.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.profileButton.setOnClickListener(v -> {
            String name = binding.nameEditText.getText().toString();
            Intent intent = new Intent(MainActivity.this, ProfilActivity.class);
            intent.putExtra("FIRST_NAME", name);
            startActivity(intent);
        });

        binding.startButton.setOnClickListener(v -> {
            String name = binding.nameEditText.getText().toString();
            if (!name.isEmpty()) {
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                intent.putExtra("USER_NAME", name);
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Veuillez entrer votre nom", Toast.LENGTH_SHORT).show();
            }
        });
    }

}