package org.example;

public class Question {
    private int id;
    private String question;
    private String[] options;
    private int answerIndex;

    public Question(int id, String question, String[] options, int answerIndex) {
        this.id = id;
        this.question = question;
        this.options = options;
        this.answerIndex = answerIndex;
    }

    public int getId() { return id; }
    public String getQuestion() { return question; }
    public String[] getOptions() { return options; }
    public int getAnswerIndex() { return answerIndex; }
}
