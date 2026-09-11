import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ResultPanel extends JPanel
{
    private Main main;

    public ResultPanel(
            Main main,
            ArrayList<Question> questions,
            int score,
            int total,
            long timeTaken,
            ArrayList<Integer> userAnswers)
    {
        this.main = main;

        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(25, 40, 25, 40));

        JLabel title = new JLabel(
                "EXAMINATION RESULT",
                SwingConstants.CENTER
        );

        title.setFont(new Font("Arial", Font.BOLD, 28));

        add(title, BorderLayout.NORTH);

        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));

        JLabel scoreLabel = new JLabel(
                "Score: " + score + " out of " + total
        );

        scoreLabel.setFont(new Font("Arial", Font.BOLD, 22));
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        long minutes = timeTaken / 60;
        long seconds = timeTaken % 60;

        JLabel timeLabel = new JLabel(
                String.format(
                        "Time Taken: %02d minutes %02d seconds",
                        minutes,
                        seconds
                )
        );

        timeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        resultPanel.add(scoreLabel);
        resultPanel.add(Box.createVerticalStrut(10));
        resultPanel.add(timeLabel);
        resultPanel.add(Box.createVerticalStrut(25));

        JTextArea breakdown = new JTextArea();

        breakdown.setEditable(false);
        breakdown.setFont(new Font("Monospaced", Font.PLAIN, 14));

        StringBuilder resultText = new StringBuilder();

        resultText.append("ANSWER BREAKDOWN\n");
        resultText.append("==============================\n\n");

        int correct = 0;
        int incorrect = 0;
        int unanswered = 0;

        for (int i = 0; i < questions.size(); i++)
        {
            Question question = questions.get(i);

            int userAnswer = userAnswers.get(i);
            int correctAnswer = question.getCorrectAnswer();

            resultText.append("Question ")
                    .append(i + 1)
                    .append(": ");

            if (userAnswer == correctAnswer)
            {
                resultText.append("CORRECT");
                correct++;
            }
            else if (userAnswer == -1)
            {
                resultText.append("NOT ANSWERED");
                unanswered++;
            }
            else
            {
                resultText.append("INCORRECT");
                incorrect++;
            }

            resultText.append("\n");

            resultText.append("Your Answer: ");

            if (userAnswer == -1)
            {
                resultText.append("Not Answered");
            }
            else
            {
                resultText.append(
                        question.getOptions()[userAnswer]
                );
            }

            resultText.append("\n");

            resultText.append("Correct Answer: ")
                    .append(question.getOptions()[correctAnswer])
                    .append("\n\n");
        }

        resultText.append("==============================\n");
        resultText.append("Correct: ").append(correct).append("\n");
        resultText.append("Incorrect: ").append(incorrect).append("\n");
        resultText.append("Not Answered: ").append(unanswered).append("\n");

        breakdown.setText(resultText.toString());

        JScrollPane scrollPane = new JScrollPane(breakdown);

        resultPanel.add(scrollPane);

        add(resultPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();

        JButton logoutButton = new JButton("Logout");

        logoutButton.addActionListener(e ->
        {
            main.showLogin();
        });

        bottomPanel.add(logoutButton);

        add(bottomPanel, BorderLayout.SOUTH);
    }
}
