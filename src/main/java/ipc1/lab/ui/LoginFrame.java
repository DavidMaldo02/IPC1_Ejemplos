package ipc1.lab.ui;

import ipc1.lab.common.State;
import ipc1.lab.user.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    JTextField codeField;
    JPasswordField passwordField;

    public LoginFrame() throws HeadlessException {
        setTitle("Login");
        setSize(new Dimension(750, 500));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Layout
        GridLayout layout = new GridLayout(6, 1, 0, 32);
        // Panel
        JPanel loginPanel = new JPanel(layout);
        loginPanel.setBackground(Pallete.background);
        loginPanel.setBorder(new EmptyBorder(32, 32, 32, 32));
        this.setContentPane(loginPanel);
        // Title
        JLabel title = new JLabel("Bienvenido!");
        title.setForeground(Pallete.text);
        title.setFont(Fonts.title);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(title);
        JLabel codeLabel = new JLabel("Código");
        codeLabel.setForeground(Pallete.text);
        codeLabel.setFont(Fonts.input);
        add(codeLabel);

        codeField = new JTextField();
        codeField.setSize(450, 50);
        add(codeField);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(Pallete.text);
        passwordLabel.setFont(Fonts.input);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setSize(450, 50);
        add(passwordField);

        JButton btLogin = new JButton("Iniciar Sesión");
        btLogin.setForeground(Pallete.background);
        btLogin.setBackground(Pallete.text);
        btLogin.setFont(Fonts.normal);
        btLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (User user : State.users) {
                    if (user.getCode().equals(codeField.getText()) &&
                            user.getPassword().equals(new String(passwordField.getPassword()))
                    ) {
                        State.currentUser = user;
                        break;
                    }
                }

                if (State.currentUser == null) {
                    JOptionPane.showMessageDialog(null, "Credenciales Incorrectas!");
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
        add(btLogin);
    }
}
