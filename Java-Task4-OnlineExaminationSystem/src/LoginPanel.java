import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel
{
    private Main main;

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginPanel(Main main)
    {
        this.main = main;

        setLayout(new GridBagLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("ONLINE EXAMINATION SYSTEM");
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Student Login");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 18));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);

        JPanel usernamePanel = new JPanel(new FlowLayout());
        usernamePanel.add(new JLabel("Username:"));
        usernamePanel.add(usernameField);

        JPanel passwordPanel = new JPanel(new FlowLayout());
        passwordPanel.add(new JLabel("Password:"));
        passwordPanel.add(passwordField);

        JButton loginButton = new JButton("Login");

        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        loginButton.addActionListener(e -> login());

        panel.add(title);
        panel.add(Box.createVerticalStrut(15));
        panel.add(subtitle);
        panel.add(Box.createVerticalStrut(25));
        panel.add(usernamePanel);
        panel.add(passwordPanel);
        panel.add(Box.createVerticalStrut(15));
        panel.add(loginButton);

        add(panel);
    }

    private void login()
    {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.equals(main.getUser().getUsername())
                && password.equals(main.getUser().getPassword()))
        {
            usernameField.setText("");
            passwordField.setText("");

            main.showProfile();
        }
        else
        {
            JOptionPane.showMessageDialog(
                    main,
                    "Invalid username or password.",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
