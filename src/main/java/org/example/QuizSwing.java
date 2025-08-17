package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class QuizSwing extends JFrame implements ActionListener {
    private List<Question> questionList;
    private int index = 0;
    private int score = 0;
    private JButton nextBtn;
    private JRadioButton[] options;
    private ButtonGroup bg;
    private JLabel questionLabel;
    private String username;

    public QuizSwing() throws Exception {
        username = JOptionPane.showInputDialog(this, "Enter your name:");
        if (username == null || username.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name required!");
            System.exit(0);
        }

        QuizDAO dao = new QuizDAO();
        questionList = dao.getAllQuestions();

        questionLabel = new JLabel("Question will appear here");
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(questionLabel);

        options = new JRadioButton[4];
        bg = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            bg.add(options[i]);
            add(options[i]);
        }

        nextBtn = new JButton("Next");
        nextBtn.addActionListener(this);
        add(nextBtn);

        setLayout(new GridLayout(6, 1));
        setSize(500, 300);
        setTitle("Online Quiz App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        loadQuestion();
    }

    private void loadQuestion() {
        bg.clearSelection();
        if (index < questionList.size()) {
            Question q = questionList.get(index);
            questionLabel.setText((index + 1) + ". " + q.getQuestion());
            String[] ops = q.getOptions();
            for (int i = 0; i < ops.length; i++) {
                options[i].setText(ops[i]);
            }
        } else {
            finishQuiz();
        }
    }

    public void actionPerformed(ActionEvent e) {
        Question current = questionList.get(index);
        int selected = -1;

        for (int i = 0; i < 4; i++) {
            if (options[i].isSelected()) {
                selected = i + 1;  // 1-based
                break;
            }
        }
        if (selected == current.getAnswerIndex()) {
            score++;
        }
        index++;
        loadQuestion();
    }

    private void finishQuiz() {
        try {
            new QuizDAO().saveScore(username, score);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        JOptionPane.showMessageDialog(this, "Quiz Completed!\n" +
                "User: " + username + "\nScore: " + score);
        System.exit(0);
    }

    public static void main(String[] args) {
        try {
            new QuizSwing();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

