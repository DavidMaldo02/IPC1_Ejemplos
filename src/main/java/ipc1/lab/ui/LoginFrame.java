package ipc1.lab.ui;

import ipc1.lab.common.State;
import ipc1.lab.user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    public LoginFrame() throws HeadlessException {
        setTitle("Login");
        setSize(new Dimension(750, 500));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Layout
        GridLayout layout = new GridLayout(6, 1, 0, 24);

        // Panel
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(layout);
        loginPanel.setBackground(Pallete.background);
        setContentPane(loginPanel);

        // Content
        JLabel title = new JLabel("Bienvenido!");
        title.setForeground(Pallete.text);
        title.setFont(Fonts.title);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title);

        JLabel codeLabel = new JLabel("Código");
        codeLabel.setForeground(Pallete.text);
        codeLabel.setFont(Fonts.input);
        add(codeLabel);

        JTextField codeField = new JTextField();
        codeField.setForeground(Pallete.text);
        codeLabel.setFont(Fonts.normal);
        codeField.setSize(450, 50);
        add(codeField);

        JLabel passwordLabel = new JLabel("Contraseña");
        passwordLabel.setForeground(Pallete.text);
        passwordLabel.setFont(Fonts.input);
        add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setForeground(Pallete.background);
        passwordLabel.setFont(Fonts.normal);
        passwordField.setSize(450, 50);
        add(passwordField);

        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.setForeground(Pallete.text);
        loginButton.setBackground(Pallete.primary);
        loginButton.setFont(Fonts.normal);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (User user: State.users) {
                    if (user.getCode().equals(codeField.getText()) &&
                            user.getPassword().equals(new String(passwordField.getPassword()))
                    ) {
                        State.currentUser = user;
                        break;
                    }
                }

                if (State.currentUser == null) {
                    JOptionPane.showMessageDialog(null, "Credenciales incorrectas!");
                    return;
                }

                if (State.currentUser.getRole().equals("admin")) {
                    new AdminFrame();
                } else {
                    new ResearcherFrame();
                }
                setVisible(false);
                codeField.setText("");
                passwordField.setText("");
            }
        });
        add(loginButton);
    }
}
