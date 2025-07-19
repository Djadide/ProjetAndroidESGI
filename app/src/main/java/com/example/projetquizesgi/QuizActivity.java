package com.example.projetquizesgi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.projetquizesgi.databinding.ActivityQuizBinding;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private ActivityQuizBinding binding;
    private List<Question> questions;
    private int currentQuestionIndex;
    private int score;

    private long startTime;
    private Handler timerHandler = new Handler();
    private Runnable timerRunnable;

    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuizBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userName = getIntent().getStringExtra("USER_NAME");

        startTime = System.currentTimeMillis();

        questions = new ArrayList<>();
        questions.add(new Question(
                "Quelle est la capitale de la France ?", Arrays.asList("London", "Paris", "Berlin"), 1));
        questions.add(new Question("C'est quoi 2 + 2 ?", Arrays.asList("3", "4", "5"), 1));

        currentQuestionIndex = 0;
        score = 0;
        displayQuestion();

        binding.nextButton.setOnClickListener(v -> checkAnswer());


        timerRunnable = () -> {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;

            timerHandler.postDelayed(timerRunnable, 1000);
        };
        timerHandler.postDelayed(timerRunnable, 0);
    }

    private void displayQuestion() {
        Question question = questions.get(currentQuestionIndex);
        binding.questionTextView.setText(question.getQuestion());
        binding.option1RadioButton.setText(question.getOptions().get(0));
        binding.option2RadioButton.setText(question.getOptions().get(1));
        binding.option3RadioButton.setText(question.getOptions().get(2));
    }

    private void checkAnswer() {
        int selectedOptionId = binding.optionsRadioGroup.getCheckedRadioButtonId();
        if (selectedOptionId == -1) {
            Toast.makeText(this, "Veuillez choisir une réponse", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton selectedRadioButton = findViewById(selectedOptionId);
        int selectedOptionIndex = binding.optionsRadioGroup.indexOfChild(selectedRadioButton);

        if (selectedOptionIndex == questions.get(currentQuestionIndex).getCorrectOptionIndex()) {
            score++;
        }

        if (currentQuestionIndex < questions.size() - 1) {
            currentQuestionIndex++;
            displayQuestion();
            binding.optionsRadioGroup.clearCheck();
        } else {
            timerHandler.removeCallbacks(timerRunnable);
            long elapsedTime = System.currentTimeMillis() - startTime;

            Intent intent = new Intent(QuizActivity.this, ResultatActivity.class);
            intent.putExtra("SCORE", score);
            intent.putExtra("USER_NAME", userName);
            intent.putExtra("CHRONO", elapsedTime);
            startActivity(intent);
            finish();
        }
    }
}
