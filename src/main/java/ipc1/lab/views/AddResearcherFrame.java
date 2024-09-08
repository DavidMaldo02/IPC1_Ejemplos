package ipc1.lab.views;

import ipc1.lab.controllers.UserController;
import ipc1.lab.models.Researcher;
import ipc1.lab.ui.Fonts;
import ipc1.lab.ui.Input;
import ipc1.lab.ui.Pallete;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddResearcherFrame extends JFrame implements ActionListener {
    JButton addBtn;
    Input nameInput;
    Input genreInput;
    Input passwordInput;
    DefaultTableModel tableModel;

    public AddResearcherFrame(DefaultTableModel tableModel) {
        setTitle("Agregar Investigador");
        setSize(500, 750);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        this.tableModel = tableModel;

        GridLayout layout = new GridLayout(5, 1, 12, 24);
        JPanel panel = new JPanel(layout);
        panel.setBackground(Pallete.background);
        panel.setBorder(new EmptyBorder(16, 24, 16, 24));
        setContentPane(panel);

        JLabel title = new JLabel("Agregar Investigador", SwingConstants.CENTER);
        title.setFont(Fonts.title);
        title.setForeground(Pallete.text);
        add(title);

        nameInput = new Input("Nombre");
        genreInput = new Input("Genero");
        passwordInput = new Input("Contraseña");
        add(nameInput);
        add(genreInput);
        add(passwordInput);

        addBtn = new JButton("Agregar Investigador");
        addBtn.setBackground(Pallete.primary);
        addBtn.setForeground(Pallete.background);
        addBtn.setFont(Fonts.normal);
        addBtn.addActionListener(this);
        add(addBtn);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == addBtn) {
            String ID = String.format("QI-%02d", UserController.users.size() - 1);
            UserController.users.add(new Researcher(ID, passwordInput.getText(), nameInput.getText(), genreInput.getText().charAt(0), 0));
            tableModel.addRow(new String[]{
                    ID,
                    nameInput.getText(),
                    genreInput.getText(),
                    Integer.toString(0)
            });
            JOptionPane.showMessageDialog(null, "Investigador agregado con éxito");
            this.dispose();
        }
    }

}
