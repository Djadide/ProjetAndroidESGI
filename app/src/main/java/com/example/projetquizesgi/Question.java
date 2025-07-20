package com.example.projetquizesgi;

import java.util.List;
import java.util.Set;

public class Question {
    private String question;
    private List<String> options;
    private Set<Integer> correctOptionIndexes;

    public Question(String question, List<String> options, Set<Integer> correctOptionIndexes) {
        this.question = question;
        this.options = options;
        this.correctOptionIndexes = correctOptionIndexes;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public Set<Integer> getCorrectOptionIndexes() {
        return correctOptionIndexes;
    }
}
