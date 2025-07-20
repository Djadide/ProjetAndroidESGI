package com.example.projetquizesgi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.projetquizesgi.databinding.ActivityQuizBinding;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QuizActivity extends AppCompatActivity {

    private ActivityQuizBinding binding;
    private List<Question> questions;
    private int currentQuestionIndex;
    private int score;

    private long startTime;
    private Handler timerHandler = new Handler();
    private Runnable timerRunnable;

    private String userName;
    private CheckBox option1CheckBox;
    private CheckBox option2CheckBox;
    private CheckBox option3CheckBox;
    private CheckBox option4CheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuizBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        option1CheckBox = binding.option1CheckBox;
        option2CheckBox = binding.option2CheckBox;
        option3CheckBox = binding.option3CheckBox;
        option4CheckBox = binding.option4CheckBox;

        userName = getIntent().getStringExtra("USER_NAME");
        startTime = System.currentTimeMillis();

        questions = new ArrayList<>();
        questions.add(new Question(
                "Quel est le langage recommandé aujourd’hui pour Android ?",
                Arrays.asList("Swift", "Python", "Java", "Kotlin"),
                new HashSet<>(Set.of(3))
        ));
        questions.add(new Question(
                "Quel attribut rend une image accessible (accessibilité) ?",
                Arrays.asList("android:contentDescription", "android:src", "android:id", "android:text"),
                new HashSet<>(Set.of(0))
        ));
        questions.add(new Question(
                "Quel est le rôle du ViewModel ?",
                Arrays.asList("Séparer UI et logique", "Créer le layout", "Gérer les données", "Dessiner les composants"),
                new HashSet<>(Set.of(0, 2))
        ));
        questions.add(new Question(
                "Méthodes du cycle de vie d’une Activity :",
                Arrays.asList("onCompile()", "onCreate()", "onFinish()", "onPause()"),
                new HashSet<>(Set.of(1, 3))
        ));
        questions.add(new Question(
                "Pourquoi utiliser ViewBinding ?",
                Arrays.asList("Simplifie l’accès au layout", "Remplace findViewById()", "Remplace AndroidManifest.xml", "Accès type-safe aux vues"),
                new HashSet<>(Set.of(0, 1, 3))
        ));
        questions.add(new Question(
                "À quoi sert Gradle ?",
                Arrays.asList("Gérer les dépendances", "Compiler le projet", "Tester le matériel", "Créer des images"),
                new HashSet<>(Set.of(0, 1))
        ));
        questions.add(new Question(
                "Quelle unité utiliser pour la taille du texte ?",
                Arrays.asList("px", "mm", "sp", "dp"),
                new HashSet<>(Set.of(2))
        ));
        questions.add(new Question(
                "Pourquoi structurer une app en couches ?",
                Arrays.asList("Lisibilité", "Maintenance", "Évolutivité", "Design UI"),
                new HashSet<>(Set.of(0, 1, 2))
        ));
        questions.add(new Question(
                "Un bouton peut être récupéré avec :",
                Arrays.asList("ViewBinding", "findViewById()", "getButton()", "getContentView()"),
                new HashSet<>(Set.of(0, 1))
        ));
        questions.add(new Question(
                "Pourquoi demander les permissions à l’exécution ?",
                Arrays.asList("Respecter la vie privée", "Éviter les crashs", "Obligatoire depuis Android 6", "Améliorer le design"),
                new HashSet<>(Set.of(0, 1, 2))
        ));
        questions.add(new Question(
                "Une interface bien conçue respecte :",
                Arrays.asList("Le contraste", "Les marges en dp", "La taille de texte en sp", "Les classes Java internes"),
                new HashSet<>(Set.of(0, 1, 2))
        ));
        questions.add(new Question(
                "Quels dossiers trouve-t-on dans un projet Android ?",
                Arrays.asList("java/", "res/", "css/", "bin/"),
                new HashSet<>(Set.of(0, 1))
        ));
        questions.add(new Question(
                "Quels outils sont inclus dans Android Studio ?",
                Arrays.asList("Word", "Profiler", "AVD Manager", "Logcat"),
                new HashSet<>(Set.of(1, 2, 3))
        ));
        questions.add(new Question(
                "Pourquoi désenregistrer un BroadcastReceiver ?",
                Arrays.asList("Éviter les fuites mémoire", "Améliorer l’accessibilité", "Réduire la taille de l’APK", "Libérer des ressources"),
                new HashSet<>(Set.of(0, 3))
        ));
        questions.add(new Question(
                "Que contient le fichier `activity_main.xml` ?",
                Arrays.asList("Le layout de l’activité principale", "Le code métier", "Le build script", "Les permissions"),
                new HashSet<>(Set.of(0))
        ));
        questions.add(new Question(
                "Une Activity est :",
                Arrays.asList("Un écran interactif", "Un point d’entrée d’une app", "Un fichier XML", "Un package"),
                new HashSet<>(Set.of(0, 1))
        ));
        questions.add(new Question(
                "Quelle instruction permet d’afficher un message court à l'utilisateur ?",
                Arrays.asList("AlertDialog", "Snackbar", "console.log", "Toast"),
                new HashSet<>(Set.of(1, 3))
        ));
        questions.add(new Question(
                "MVC signifie :",
                Arrays.asList("Model Version Control", "Main Visual Container", "Model View Controller", "Mobile View Controller"),
                new HashSet<>(Set.of(2))
        ));
        questions.add(new Question(
                "Qui est la mascotte officielle d’Android depuis 2024 ?",
                Arrays.asList("The Bot", "Android Man", "Droidy", "Bugdroid"),
                new HashSet<>(Set.of(0))
        ));
        questions.add(new Question(
                "Quel fichier déclare les activités et permissions ?",
                Arrays.asList("activity_main.xml", "settings.gradle", "build.gradle", "AndroidManifest.xml"),
                new HashSet<>(Set.of(3))
        ));
        questions.add(new Question(
                "Quel composant écoute des événements système ?",
                Arrays.asList("IntentFilter", "Fragment", "BroadcastReceiver", "Service"),
                new HashSet<>(Set.of(2))
        ));

        currentQuestionIndex = 0;
        score = 0;
        displayQuestion();

        binding.nextButton.setOnClickListener(v -> checkAnswer());

        timerRunnable = () -> {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;
            // affichage optionnel dans un TextView
            timerHandler.postDelayed(timerRunnable, 1000);
        };
        timerHandler.postDelayed(timerRunnable, 0);
    }

    private void displayQuestion() {
        Question question = questions.get(currentQuestionIndex);
        binding.questionTextView.setText(question.getQuestion());
        List<String> opts = question.getOptions();

        option1CheckBox.setText(opts.get(0));
        option1CheckBox.setChecked(false);
        option1CheckBox.setVisibility(opts.size() > 0 ? View.VISIBLE : View.GONE);

        option2CheckBox.setText(opts.get(1));
        option2CheckBox.setChecked(false);
        option2CheckBox.setVisibility(opts.size() > 1 ? View.VISIBLE : View.GONE);

        option3CheckBox.setText(opts.get(2));
        option3CheckBox.setChecked(false);
        option3CheckBox.setVisibility(opts.size() > 2 ? View.VISIBLE : View.GONE);

        if (opts.size() > 3) {
            option4CheckBox.setText(opts.get(3));
            option4CheckBox.setVisibility(View.VISIBLE);
            option4CheckBox.setChecked(false);
        } else {
            option4CheckBox.setVisibility(View.GONE);
        }
    }

    private void checkAnswer() {
        List<Integer> selectedIndexes = new ArrayList<>();
        if (option1CheckBox.isChecked()) selectedIndexes.add(0);
        if (option2CheckBox.isChecked()) selectedIndexes.add(1);
        if (option3CheckBox.isChecked()) selectedIndexes.add(2);
        if (option4CheckBox.getVisibility() == View.VISIBLE && option4CheckBox.isChecked()) selectedIndexes.add(3);

        if (selectedIndexes.isEmpty()) {
            Toast.makeText(this, "Veuillez choisir au moins une réponse", Toast.LENGTH_SHORT).show();
            return;
        }

        Question currentQ = questions.get(currentQuestionIndex);
        Set<Integer> correct = currentQ.getCorrectOptionIndexes();

        if (correct.size() == selectedIndexes.size() && correct.containsAll(selectedIndexes)) {
            score++;
        }

        if (currentQuestionIndex < questions.size() - 1) {
            currentQuestionIndex++;
            displayQuestion();
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
