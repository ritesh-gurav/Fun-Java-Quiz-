import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class QuizGame {
    private JFrame frame;
    private JPanel panel;
    private JLabel questionLabel;
    private JRadioButton[] options;
    private ButtonGroup buttonGroup;
    private JButton nextButton;
    private JButton exitButton;
    private int currentQuestionIndex;
    private int score;

    private static final int NUMBER_OF_OPTIONS = 4;
    private static final int CORRECT_ANSWER_INDEX = 1;

    private String[][] questions = {
            {"What is the correct way to declare a variable in Java?", "int variable = value;", "variable name = value;", "variable name;", "var variable = value;"},
            {"Which of the following data types is used to store whole numbers in Java?", "int", "float", "double", "char"},
            {"What does the 'static' keyword mean in Java?", "The variable is a class variable", "The variable is a local variable", "The variable is a constant", "The variable is an instance variable"},
            {"Which keyword is used to define the superclass in Java?", "extends", "superclass", "super", "parent"},
            {"What is the output of the following code snippet?\n\nint x = 5;\nSystem.out.println(x++);\nSystem.out.println(++x);", "5\n7", "5\n6", "6\n7", "6\n6"}
    };

    public QuizGame() {
        initializeUI();
        initializeListeners();
        showNextQuestion();
        frame.setVisible(true);
    }

    private void initializeUI() {
        frame = new JFrame("Java Basics Quiz Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);

        panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1));
        panel.setBackground(new Color(0, 38, 77));

        questionLabel = new JLabel();
        questionLabel.setForeground(Color.WHITE);
        questionLabel.setFont(questionLabel.getFont().deriveFont(Font.BOLD, 20f));
        panel.add(questionLabel);

        options = new JRadioButton[NUMBER_OF_OPTIONS];
        buttonGroup = new ButtonGroup();
        for (int i = 0; i < NUMBER_OF_OPTIONS; i++) {
            options[i] = new JRadioButton();
            options[i].setForeground(Color.WHITE);
            options[i].setFont(options[i].getFont().deriveFont(18f));
            options[i].setBackground(new Color(0, 102, 204));
            options[i].setOpaque(true);
            options[i].setBorder(BorderFactory.createLineBorder(new Color(0, 77, 153), 2));
            panel.add(options[i]);
            buttonGroup.add(options[i]);
        }

        nextButton = new JButton("Next");
        nextButton.setBackground(new Color(44, 163, 95));
        nextButton.setForeground(Color.WHITE);
        nextButton.setFont(nextButton.getFont().deriveFont(18f));
        panel.add(nextButton);

        exitButton = new JButton("Exit");
        exitButton.setBackground(new Color(165, 42, 42));
        exitButton.setForeground(Color.WHITE);
        exitButton.setFont(exitButton.getFont().deriveFont(18f));
        panel.add(exitButton);

        frame.add(panel);
    }

    private void initializeListeners() {
        nextButton.addActionListener(this::onNextButtonClick);
        exitButton.addActionListener(e -> System.exit(0));
    }

    private void onNextButtonClick(ActionEvent e) {
        checkAnswer();
        showNextQuestion();
    }

    private void showNextQuestion() {
        if (currentQuestionIndex < questions.length) {
            questionLabel.setText(questions[currentQuestionIndex][0]);
            for (int i = 0; i < NUMBER_OF_OPTIONS; i++) {
                options[i].setText(questions[currentQuestionIndex][i + 1]);
                options[i].setSelected(false);
            }
            currentQuestionIndex++;
        } else {
            showResult();
        }
    }

    private void checkAnswer() {
        boolean answerSelected = false;
        for (int i = 0; i < NUMBER_OF_OPTIONS; i++) {
            if (options[i].isSelected()) {
                answerSelected = true;
                if (options[i].getText().equals(questions[currentQuestionIndex - 1][CORRECT_ANSWER_INDEX])) {
                    score++;
                }
            }
        }

        if (!answerSelected) {
            JOptionPane.showMessageDialog(frame, "Please select an answer.", "Error", JOptionPane.WARNING_MESSAGE);
        }

        buttonGroup.clearSelection();
    }

    private void showResult() {
        String message = "Quiz Completed!\nYour Score: " + score + " out of " + questions.length;
        JOptionPane.showMessageDialog(frame, message, "Result", JOptionPane.INFORMATION_MESSAGE);
        nextButton.setVisible(false);
        exitButton.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(QuizGame::new);
    }
}
