import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class Main extends JFrame
{
    private CardLayout cardLayout;
    private JPanel mainPanel;

    private User user;

    private ArrayList<Question> questions;

    private ExamPanel examPanel;

    public Main()
    {
        user = new User("student", "1234", "Student");

        createQuestions();

        setTitle("Online Examination System");
        setSize(900, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(new LoginPanel(this), "LOGIN");
        mainPanel.add(new ProfilePanel(this), "PROFILE");

        add(mainPanel);

        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                if (examPanel != null && examPanel.isExamRunning())
                {
                    int choice = JOptionPane.showConfirmDialog(
                            Main.this,
                            "Are you sure you want to quit the exam?",
                            "Confirm Exit",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );

                    if (choice == JOptionPane.YES_OPTION)
                    {
                        examPanel.stopTimer();
                        System.exit(0);
                    }
                }
                else
                {
                    int choice = JOptionPane.showConfirmDialog(
                            Main.this,
                            "Are you sure you want to exit?",
                            "Confirm Exit",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (choice == JOptionPane.YES_OPTION)
                    {
                        System.exit(0);
                    }
                }
            }
        });
    }

    private void createQuestions()
    {
        questions = new ArrayList<>();

        questions.add(new Question(
                "Which language is primarily used to develop Android applications?",
                new String[]{"Java", "HTML", "CSS", "SQL"},
                0
        ));

        questions.add(new Question(
                "Which keyword is used to create a class in Java?",
                new String[]{"function", "class", "define", "struct"},
                1
        ));

        questions.add(new Question(
                "Which method is the entry point of a Java application?",
                new String[]{"start()", "run()", "main()", "execute()"},
                2
        ));

        questions.add(new Question(
                "Which data type is used to store whole numbers in Java?",
                new String[]{"float", "String", "boolean", "int"},
                3
        ));

        questions.add(new Question(
                "Which symbol is used for single-line comments in Java?",
                new String[]{"//", "/*", "#", "<!--"},
                0
        ));

        questions.add(new Question(
                "Which keyword is used to inherit a class in Java?",
                new String[]{"implements", "extends", "inherits", "super"},
                1
        ));

        questions.add(new Question(
                "Which collection does not allow duplicate elements?",
                new String[]{"List", "ArrayList", "Set", "Vector"},
                2
        ));

        questions.add(new Question(
                "Which package contains Swing components?",
                new String[]{"java.io", "java.util", "java.net", "javax.swing"},
                3
        ));

        questions.add(new Question(
                "Which keyword is used to define a constant variable?",
                new String[]{"static", "final", "constant", "fixed"},
                1
        ));

        questions.add(new Question(
                "Which operator checks equality in Java?",
                new String[]{"=", "==", "!=", "==="},
                1
        ));
    }

    public void showLogin()
    {
        if (examPanel != null)
        {
            examPanel.stopTimer();
            examPanel = null;
        }

        cardLayout.show(mainPanel, "LOGIN");
    }

    public void showProfile()
    {
        ProfilePanel profilePanel = new ProfilePanel(this);

        mainPanel.add(profilePanel, "PROFILE");

        cardLayout.show(mainPanel, "PROFILE");
    }

    public void startExam()
    {
        examPanel = new ExamPanel(this, questions);

        mainPanel.add(examPanel, "EXAM");

        cardLayout.show(mainPanel, "EXAM");
    }

    public void showResult(int score, int total, long timeTaken,
                           ArrayList<Integer> userAnswers)
    {
        if (examPanel != null)
        {
            examPanel.stopTimer();
        }

        ResultPanel resultPanel = new ResultPanel(
                this,
                questions,
                score,
                total,
                timeTaken,
                userAnswers
        );

        mainPanel.add(resultPanel, "RESULT");

        cardLayout.show(mainPanel, "RESULT");
    }

    public User getUser()
    {
        return user;
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() ->
        {
            Main main = new Main();
            main.setVisible(true);
        });
    }
}
