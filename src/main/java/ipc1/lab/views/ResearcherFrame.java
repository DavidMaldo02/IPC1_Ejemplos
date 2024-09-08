package ipc1.lab.views;

import ipc1.lab.controllers.UserController;
import ipc1.lab.ui.Fonts;
import ipc1.lab.ui.Pallete;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ResearcherFrame extends JFrame {
    public ResearcherFrame() throws HeadlessException {
        setTitle("Investigador");
        setSize(new Dimension(750, 500));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                UserController.currentUser = null;
                new LoginFrame().setVisible(true);
            }
        });

        // Panel
        JPanel loginPanel = new JPanel();
        loginPanel.setBackground(Pallete.background);
        loginPanel.setBorder(new EmptyBorder(32, 32, 32, 32));
        this.setContentPane(loginPanel);

        // Title
        JLabel title = new JLabel("Bienvenido " + UserController.currentUser.getCode() + "!");
        title.setForeground(Pallete.text);
        title.setFont(Fonts.title);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(title);
        setVisible(true);
    }
}
