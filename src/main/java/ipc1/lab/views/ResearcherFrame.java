package ipc1.lab.views;

import ipc1.lab.controllers.UserController;
import ipc1.lab.models.MyThread;
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
    ArrayList<Product> products = new ArrayList<>();

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
        GridLayout gridLayout = new GridLayout(5, 1, 0, 24);

        // Panel
        JPanel loginPanel = new JPanel(gridLayout);
        loginPanel.setBackground(Pallete.background);
        loginPanel.setBorder(new EmptyBorder(32, 32, 32, 32));
        this.setContentPane(loginPanel);

        // Title
        JLabel title = new JLabel("Bienvenido " + UserController.currentUser.getCode() + "!");
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

        Product product = new Product("P-01", "Carro", "Aluminio", "Verde", 5);
        products.add(product);
        products.add(product);
        products.add(product);
        progressBar1.setStringPainted(true);
        progressBar2.setStringPainted(true);
        progressBar3.setStringPainted(true);
        Thread thread = new Thread(() -> {
            for (int i = 0; i < products.size(); i++) {
                try {
                    progressBar1.setValue(0);
                    progressBar2.setValue(0);
                    progressBar3.setValue(0);
                    MyThread myThread1 = new MyThread(products.get(i), progressBar1, "ensamblaje");
                    MyThread myThread2 = new MyThread(products.get(i), progressBar2, "pintura");
                    MyThread myThread3 = new MyThread(products.get(i), progressBar3, "empaquetado");
                    myThread1.start();
                    myThread1.join();
                    myThread2.start();
                    myThread2.join();
                    myThread3.start();
                    myThread3.join();
                    counter.setText("Productos terminados: " + (i + 1));
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        thread.start();

        this.setVisible(true);
    }
}
