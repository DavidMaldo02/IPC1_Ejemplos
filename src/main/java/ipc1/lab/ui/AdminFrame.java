package ipc1.lab.ui;

import ipc1.lab.common.State;
import ipc1.lab.common.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AdminFrame extends JFrame {
    public AdminFrame() throws HeadlessException {
        setTitle("Administrador");
        setSize(new Dimension(750, 500));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                State.currentUser = null;
                Util.loginFrame.setVisible(true);
            }
        });

        // Panel
        JPanel loginPanel = new JPanel();
        loginPanel.setBackground(Pallete.background);
        setContentPane(loginPanel);

        // Title
        JLabel title = new JLabel("Bienvenido Administrador!");
        title.setForeground(Pallete.text);
        title.setFont(Fonts.title);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title);

        setVisible(true);
    }
}
