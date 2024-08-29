package ipc1.lab.ui;

import ipc1.lab.common.State;
import ipc1.lab.common.Util;
import ipc1.lab.user.Researcher;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AdminFrame extends JFrame implements ActionListener {
    JButton deleteBtn;
    JTable table;
    DefaultTableModel tableModel;

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
        BorderLayout borderLayout = new BorderLayout(16, 24);

        // Panel
        JPanel loginPanel = new JPanel(borderLayout);
        loginPanel.setBackground(Pallete.background);
        loginPanel.setBorder(new EmptyBorder(32, 32, 32, 32));
        this.setContentPane(loginPanel);

        // Title
        JLabel title = new JLabel("Bienvenido Administrador!");
        title.setForeground(Pallete.text);
        title.setFont(Fonts.title);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title, BorderLayout.NORTH);
        setVisible(true);

        // Researcher Table
        String[] userAttributes = {"Codigo", "Nombre", "Genero", "Numero de experimentos"};
        String[][] usersData = new String[State.users.size() - 1][4];
        // Fill table
        for (int i = 0; i < State.users.size(); i++) {
            if (State.users.get(i) instanceof Researcher researcher) {
                usersData[i - 1][0] = researcher.getCode();
                usersData[i - 1][1] = researcher.getName();
                usersData[i - 1][2] = String.valueOf(researcher.getGenre());
                usersData[i - 1][3] = String.valueOf(researcher.getExperimentsCount());
            } else {
                continue;
            }
        }

        tableModel = new DefaultTableModel(usersData, userAttributes);
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        DefaultCategoryDataset top3RearchersData = new DefaultCategoryDataset();

        deleteBtn = new JButton("Eliminar");
        deleteBtn.setForeground(Pallete.text);
        deleteBtn.setBackground(Pallete.accent);
        deleteBtn.setFont(Fonts.normal);
        deleteBtn.addActionListener(this);

        add(deleteBtn, BorderLayout.SOUTH);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteBtn) {
            if (table.getSelectedRow() != -1) {
                State.users.remove(table.getSelectedRow() + 1);
                tableModel.removeRow(table.getSelectedRow());
                JOptionPane.showMessageDialog(null, "Usuario eliminado con exito");
            }
        }
    }
}
