import javax.swing.*;
import java.awt.*;

public class ProfilePanel extends JPanel
{
    private Main main;

    private JTextField displayNameField;
    private JPasswordField passwordField;

    public ProfilePanel(Main main)
    {
        this.main = main;

        setLayout(new GridBagLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("PROFILE UPDATE");
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel instruction = new JLabel("Update your details before starting the exam.");
        instruction.setAlignmentX(Component.CENTER_ALIGNMENT);

        displayNameField = new JTextField(main.getUser().getDisplayName(), 20);
        passwordField = new JPasswordField(main.getUser().getPassword(), 20);

        JPanel namePanel = new JPanel(new FlowLayout());
        namePanel.add(new JLabel("Display Name:"));
        namePanel.add(displayNameField);

        JPanel passwordPanel = new JPanel(new FlowLayout());
        passwordPanel.add(new JLabel("Password:"));
        passwordPanel.add(passwordField);

        JButton startButton = new JButton("Save & Start Exam");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        startButton.addActionListener(e -> updateProfile());

        logoutButton.addActionListener(e -> main.showLogin());

        panel.add(title);
        panel.add(Box.createVerticalStrut(15));
        panel.add(instruction);
        panel.add(Box.createVerticalStrut(25));
        panel.add(namePanel);
        panel.add(passwordPanel);
        panel.add(Box.createVerticalStrut(15));
        panel.add(startButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(logoutButton);

        add(panel);
    }

    private void updateProfile()
    {
        String displayName = displayNameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (displayName.isEmpty() || password.isEmpty())
        {
            JOptionPane.showMessageDialog(
                    main,
                    "Display name and password cannot be empty.",
                    "Invalid Input",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        main.getUser().setDisplayName(displayName);
        main.getUser().setPassword(password);

        main.startExam();
    }
}
