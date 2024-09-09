package ipc1.lab.views;

import ipc1.lab.controllers.UserController;
import ipc1.lab.models.ProcessThread;
import ipc1.lab.models.Product;
import ipc1.lab.ui.Fonts;
import ipc1.lab.ui.Pallete;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class ResearcherFrame extends JFrame {
    ArrayList<Product> progressLine = new ArrayList<>();

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
        // Layout
        GridLayout layout = new GridLayout(5, 1, 0, 24);

        // Panel
        JPanel loginPanel = new JPanel(layout);
        loginPanel.setBackground(Pallete.background);
        loginPanel.setBorder(new EmptyBorder(32, 32, 32, 32));
        this.setContentPane(loginPanel);

        // Title
        JLabel title = new JLabel("Bienvenido ");
        title.setForeground(Pallete.text);
        title.setFont(Fonts.title);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(title);

        JProgressBar progressBar1 = new JProgressBar(0, 100);
        this.add(progressBar1);
        JProgressBar progressBar2 = new JProgressBar(0, 100);
        this.add(progressBar2);
        JProgressBar progressBar3 = new JProgressBar(0, 100);
        this.add(progressBar3);

        // Product counter
        JLabel counter = new JLabel("Productos terminados: 0");
        counter.setForeground(Pallete.text);
        counter.setFont(Fonts.title2);
        counter.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(counter);

        this.setVisible(true);
        Product product = new Product("P-01", "Carrito", "Aluminio", "Verde", 5);
        progressLine.add(product);
        progressLine.add(product);
        progressLine.add(product);
        progressBar1.setStringPainted(true);
        progressBar2.setStringPainted(true);
        progressBar3.setStringPainted(true);
        for (int i = 0; i < progressLine.size(); i++) {
            try {
                progressBar1.setValue(0);
                progressBar2.setValue(0);
                progressBar3.setValue(0);
                ProcessThread processThread1 = new ProcessThread(progressLine.get(i), progressBar1, "ensamblaje");
                ProcessThread processThread2 = new ProcessThread(progressLine.get(i), progressBar2, "pintura");
                ProcessThread processThread3 = new ProcessThread(progressLine.get(i), progressBar3, "empaquetado");
                processThread1.start();
                processThread1.join();
                processThread2.start();
                processThread2.join();
                processThread3.start();
                processThread3.join();
                counter.setText("Productos terminados: " + (i + 1));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
