import javax.swing.*;
import java.awt.*;

public class QuizApplication extends JFrame {

    String[] questions = {
        "1. Which language is used for Android development?",
        "2. Which is not an OOP concept?",
        "3. Which keyword creates an object in Java?",
        "4. Which method is the entry point of Java?",
        "5. Which package contains Swing classes?"
    };

    String[][] options = {
        {"Java", "HTML", "CSS", "SQL"},
        {"Inheritance", "Polymorphism", "Compilation", "Encapsulation"},
        {"class", "new", "void", "static"},
        {"start()", "main()", "run()", "init()"},
        {"java.util", "java.io", "javax.swing", "java.net"}
    };

    int[] answers = {0, 2, 1, 1, 2};

    int currentQuestion = 0;
    int score = 0;

    JLabel questionLabel;
    JRadioButton[] radioButtons;
    ButtonGroup group;
    JButton nextButton;
    JTextField nameField;

    QuizApplication() {

        setTitle("Online Quiz Application");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        JLabel title = new JLabel("ONLINE QUIZ APPLICATION");
        title.setBounds(120, 20, 400, 35);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(new Color(20, 70, 140));
        panel.add(title);

        JLabel nameLabel = new JLabel("Student Name:");
        nameLabel.setBounds(40, 75, 120, 25);
        panel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(160, 75, 300, 28);
        panel.add(nameField);

        questionLabel = new JLabel();
        questionLabel.setBounds(40, 125, 520, 35);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 15));
        panel.add(questionLabel);

        radioButtons = new JRadioButton[4];

        group = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            radioButtons[i] = new JRadioButton();
            radioButtons[i].setBounds(60, 180 + i * 40, 450, 30);
            radioButtons[i].setBackground(Color.WHITE);
            panel.add(radioButtons[i]);
            group.add(radioButtons[i]);
        }

        nextButton = new JButton("Next");
        nextButton.setBounds(220, 355, 140, 35);
        nextButton.setBackground(new Color(30, 100, 200));
        nextButton.setForeground(Color.WHITE);
        panel.add(nextButton);

        nextButton.addActionListener(e -> nextQuestion());

        add(panel);

        loadQuestion();

        setVisible(true);
    }

    void loadQuestion() {

        questionLabel.setText(questions[currentQuestion]);

        group.clearSelection();

        for (int i = 0; i < 4; i++) {
            radioButtons[i].setText(options[currentQuestion][i]);
        }

        if (currentQuestion == questions.length - 1) {
            nextButton.setText("Submit");
        } else {
            nextButton.setText("Next");
        }
    }

    void nextQuestion() {

        int selected = -1;

        for (int i = 0; i < 4; i++) {
            if (radioButtons[i].isSelected()) {
                selected = i;
                break;
            }
        }

        if (selected == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select an answer!");
            return;
        }

        if (selected == answers[currentQuestion]) {
            score++;
        }

        if (currentQuestion < questions.length - 1) {

            currentQuestion++;
            loadQuestion();

        } else {

            String name = nameField.getText().trim();

            if (name.isEmpty()) {
                name = "Student";
            }

            JOptionPane.showMessageDialog(this,
                    "Quiz Completed!\n\nStudent: " + name
                    + "\nTotal Questions: " + questions.length
                    + "\nCorrect Answers: " + score
                    + "\nWrong Answers: " + (questions.length - score)
                    + "\nFinal Score: " + score + "/" + questions.length);

            nextButton.setEnabled(false);
            questionLabel.setText("Quiz Completed!");
            for (JRadioButton button : radioButtons) {
                button.setEnabled(false);
            }
        }
    }

    public static void main(String[] args) {
        new QuizApplication();
    }
}