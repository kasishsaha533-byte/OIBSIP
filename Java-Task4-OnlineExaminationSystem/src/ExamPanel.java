import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ExamPanel extends JPanel
{
    private Main main;

    private ArrayList<Question> questions;
    private ArrayList<Integer> userAnswers;

    private int currentQuestion = 0;

    private JRadioButton[] options;
    private ButtonGroup optionGroup;

    private JLabel questionNumberLabel;
    private JLabel questionLabel;
    private JLabel timerLabel;

    private JButton previousButton;
    private JButton nextButton;
    private JButton submitButton;

    private Timer timer;

    private int remainingSeconds = 30 * 60;
    private int totalExamSeconds = 30 * 60;

    private long examStartTime;

    private boolean examRunning = true;

    public ExamPanel(Main main, ArrayList<Question> questions)
    {
        this.main = main;
        this.questions = questions;

        userAnswers = new ArrayList<>();

        for (int i = 0; i < questions.size(); i++)
        {
            userAnswers.add(-1);
        }

        examStartTime = System.currentTimeMillis();

        setLayout(
                new BorderLayout(15, 15)
        );

        setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        30,
                        20,
                        30
                )
        );

        createTopPanel();
        createQuestionPanel();
        createBottomPanel();

        showQuestion();

        startTimer();
    }

    private void createTopPanel()
    {
        JPanel topPanel =
                new JPanel(
                        new BorderLayout()
                );

        JLabel titleLabel =
                new JLabel(
                        "ONLINE EXAMINATION"
                );

        titleLabel.setFont(
                new Font(
                        "Times New Roman",
                        Font.BOLD,
                        28
                )
        );

        timerLabel =
                new JLabel(
                        "Time Left: 30:00"
                );

        timerLabel.setFont(
                new Font(
                        "Times New Roman",
                        Font.BOLD,
                        22
                )
        );

        topPanel.add(
                titleLabel,
                BorderLayout.WEST
        );

        topPanel.add(
                timerLabel,
                BorderLayout.EAST
        );

        add(
                topPanel,
                BorderLayout.NORTH
        );
    }

    private void createQuestionPanel()
    {
        JPanel centerPanel =
                new JPanel();

        centerPanel.setLayout(
                new BoxLayout(
                        centerPanel,
                        BoxLayout.Y_AXIS
                )
        );

        /*
         * QUESTION NUMBER
         */
        questionNumberLabel =
                new JLabel();

        questionNumberLabel.setFont(
                new Font(
                        "Times New Roman",
                        Font.BOLD,
                        22
                )
        );

        questionNumberLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        questionNumberLabel.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        35
                )
        );

        centerPanel.add(
                questionNumberLabel
        );

        /*
         * Small space after question number
         */
        centerPanel.add(
                Box.createVerticalStrut(20)
        );

        /*
         * QUESTION
         */
        questionLabel =
                new JLabel();

        questionLabel.setFont(
                new Font(
                        "Times New Roman",
                        Font.BOLD,
                        24
                )
        );

        /*
         * Keep the question on the left
         */
        questionLabel.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        JPanel questionTextPanel =
                new JPanel(
                        new BorderLayout()
                );

        questionTextPanel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        questionTextPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        0,
                        0,
                        0
                )
        );

        questionTextPanel.add(
                questionLabel,
                BorderLayout.WEST
        );

        /*
         * VERY IMPORTANT:
         * Prevent this panel from expanding vertically.
         */
        questionTextPanel.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        50
                )
        );

        questionTextPanel.setPreferredSize(
                new Dimension(
                        1000,
                        45
                )
        );

        centerPanel.add(
                questionTextPanel
        );

        /*
         * Small gap between question
         * and options
         */
        centerPanel.add(
                Box.createVerticalStrut(15)
        );

        /*
         * OPTIONS
         */
        options =
                new JRadioButton[4];

        optionGroup =
                new ButtonGroup();

        for (int i = 0; i < 4; i++)
        {
            options[i] =
                    new JRadioButton();

            options[i].setFont(
                    new Font(
                            "Times New Roman",
                            Font.PLAIN,
                            20
                    )
            );

            options[i].setAlignmentX(
                    Component.LEFT_ALIGNMENT
            );

            /*
             * Prevent each option from
             * taking extra vertical space.
             */
            options[i].setMaximumSize(
                    new Dimension(
                            Integer.MAX_VALUE,
                            38
                    )
            );

            options[i].setPreferredSize(
                    new Dimension(
                            500,
                            35
                    )
            );

            optionGroup.add(
                    options[i]
            );

            centerPanel.add(
                    options[i]
            );

            /*
             * Small gap between options
             */
            if (i < 3)
            {
                centerPanel.add(
                        Box.createVerticalStrut(5)
                );
            }
        }

        /*
         * Prevent the whole center panel
         * from distributing components
         * over the available height.
         */
        centerPanel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        add(
                centerPanel,
                BorderLayout.CENTER
        );
    }

    private void createBottomPanel()
    {
        JPanel bottomPanel =
                new JPanel(
                        new BorderLayout()
                );

        JPanel navigationPanel =
                new JPanel();

        previousButton =
                new JButton(
                        "Previous"
                );

        nextButton =
                new JButton(
                        "Next"
                );

        previousButton.setFont(
                new Font(
                        "Times New Roman",
                        Font.BOLD,
                        16
                )
        );

        nextButton.setFont(
                new Font(
                        "Times New Roman",
                        Font.BOLD,
                        16
                )
        );

        previousButton.addActionListener(e ->
        {
            saveAnswer();

            if (currentQuestion > 0)
            {
                currentQuestion--;

                showQuestion();
            }
        });

        nextButton.addActionListener(e ->
        {
            saveAnswer();

            if (currentQuestion <
                    questions.size() - 1)
            {
                currentQuestion++;

                showQuestion();
            }
        });

        navigationPanel.add(
                previousButton
        );

        navigationPanel.add(
                nextButton
        );

        submitButton =
                new JButton(
                        "Submit Exam"
                );

        submitButton.setFont(
                new Font(
                        "Times New Roman",
                        Font.BOLD,
                        16
                )
        );

        submitButton.addActionListener(e ->
        {
            confirmSubmit();
        });

        bottomPanel.add(
                navigationPanel,
                BorderLayout.WEST
        );

        bottomPanel.add(
                submitButton,
                BorderLayout.EAST
        );

        add(
                bottomPanel,
                BorderLayout.SOUTH
        );
    }

    private void showQuestion()
    {
        Question question =
                questions.get(
                        currentQuestion
                );

        questionNumberLabel.setText(
                "Question "
                        + (currentQuestion + 1)
                        + " of "
                        + questions.size()
        );

        /*
         * HTML allows the question to wrap
         * instead of being cut off.
         */
        questionLabel.setText(
                "<html><div style='width:1000px;'>"
                        + question.getQuestion()
                        + "</div></html>"
        );

        String[] questionOptions =
                question.getOptions();

        optionGroup.clearSelection();

        for (int i = 0; i < 4; i++)
        {
            options[i].setText(
                    questionOptions[i]
            );
        }

        int savedAnswer =
                userAnswers.get(
                        currentQuestion
                );

        if (savedAnswer != -1)
        {
            options[savedAnswer]
                    .setSelected(true);
        }

        previousButton.setEnabled(
                currentQuestion > 0
        );

        nextButton.setEnabled(
                currentQuestion <
                        questions.size() - 1
        );
    }

    private void saveAnswer()
    {
        for (int i = 0;
             i < options.length;
             i++)
        {
            if (options[i].isSelected())
            {
                userAnswers.set(
                        currentQuestion,
                        i
                );

                return;
            }
        }

        userAnswers.set(
                currentQuestion,
                -1
        );
    }

    private void startTimer()
    {
        timer =
                new Timer(
                        1000,
                        e ->
                        {
                            remainingSeconds--;

                            updateTimerLabel();

                            if (remainingSeconds <= 0)
                            {
                                timer.stop();

                                JOptionPane.showMessageDialog(
                                        main,
                                        "Time is up! Your exam will be submitted automatically.",
                                        "Time Up",
                                        JOptionPane.INFORMATION_MESSAGE
                                );

                                submitExam();
                            }
                        }
                );

        timer.start();
    }

    private void updateTimerLabel()
    {
        int minutes =
                remainingSeconds / 60;

        int seconds =
                remainingSeconds % 60;

        timerLabel.setText(
                String.format(
                        "Time Left: %02d:%02d",
                        minutes,
                        seconds
                )
        );
    }

    private void confirmSubmit()
    {
        saveAnswer();

        int choice =
                JOptionPane.showConfirmDialog(
                        main,
                        "Are you sure you want to submit the exam?",
                        "Confirm Submission",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

        if (choice ==
                JOptionPane.YES_OPTION)
        {
            submitExam();
        }
    }

    private void submitExam()
    {
        if (!examRunning)
        {
            return;
        }

        examRunning = false;

        if (timer != null)
        {
            timer.stop();
        }

        saveAnswer();

        int score = 0;

        for (int i = 0;
             i < questions.size();
             i++)
        {
            if (userAnswers.get(i)
                    == questions.get(i)
                    .getCorrectAnswer())
            {
                score++;
            }
        }

        long timeTaken =
                (
                        System.currentTimeMillis()
                                - examStartTime
                ) / 1000;

        main.showResult(
                score,
                questions.size(),
                timeTaken,
                userAnswers
        );
    }

    public boolean isExamRunning()
    {
        return examRunning;
    }

    public void stopTimer()
    {
        examRunning = false;

        if (timer != null)
        {
            timer.stop();
        }
    }
}
