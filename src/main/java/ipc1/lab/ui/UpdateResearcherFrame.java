package ipc1.lab.ui;

import ipc1.lab.user.Researcher;
import ipc1.lab.user.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateResearcherFrame extends JFrame implements ActionListener {
    JButton updateBtn;
    Input nameInput;
    Input genreInput;
    Input experimentsCountInput;
    Researcher user;
    DefaultTableModel tableModel;
    int row;

    public UpdateResearcherFrame(User user, DefaultTableModel tableModel, int row) {
        setTitle("Editar Investigador");
        setSize(500, 750);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        this.user = (Researcher) user;
        this.tableModel = tableModel;
        this.row = row;

        GridLayout layout = new GridLayout(5, 1, 12, 24);
        JPanel panel = new JPanel(layout);
        panel.setBackground(Pallete.background);
        panel.setBorder(new EmptyBorder(16, 24, 16, 24));
        setContentPane(panel);

        JLabel title = new JLabel("Editar Investigador", SwingConstants.CENTER);
        title.setFont(Fonts.title);
        title.setForeground(Pallete.text);
        add(title);

        nameInput = new Input("Nombre", this.user.getName());
        genreInput = new Input("Genero", String.valueOf(this.user.getGenre()));
        experimentsCountInput = new Input("Cantidad de Experimentos", String.valueOf(this.user.getExperimentsCount()));
        add(nameInput);
        add(genreInput);
        add(experimentsCountInput);

        updateBtn = new JButton("Editar Investigador");
        updateBtn.setBackground(Pallete.primary);
        updateBtn.setForeground(Pallete.background);
        updateBtn.setFont(Fonts.normal);
        updateBtn.addActionListener(this);
        add(updateBtn);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateBtn) {
            String name = nameInput.getText();
            char genre = genreInput.getText().charAt(0);
            int experimentsCount = Integer.parseInt(experimentsCountInput.getText());
            this.user.setName(name);
            this.user.setGenre(genre);
            this.user.setExperimentsCount(experimentsCount);
            this.tableModel.setValueAt(name, row, 1);
            this.tableModel.setValueAt(genre, row, 2);
            this.tableModel.setValueAt(experimentsCount, row, 3);
            this.dispose();
        }

    }
}
