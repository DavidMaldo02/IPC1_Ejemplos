package ipc1.lab.ui;

import ipc1.lab.common.State;
import ipc1.lab.common.Util;
import ipc1.lab.user.Researcher;
import ipc1.lab.user.User;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
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
    JButton eliminarBtn;
    JButton agregarBtn;
    JButton cargaMasivaBtn;
    JButton editarBtn;
    JTable usersTable;
    DefaultTableModel usersTableModel;
    DefaultCategoryDataset top3ResearchersData;

    public AdminFrame() throws HeadlessException {
        setTitle("Administrador");
        setSize(new Dimension(1250, 750));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Util.loginFrame.setVisible(true);
                State.currentUser = null;
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

        // Researchers Table
        String[] usersAttributes = {"Código", "Nombre", "Género", "Número de Experimentos"};
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
        usersTableModel = new DefaultTableModel(usersData, usersAttributes);
        usersTable = new JTable(usersTableModel);
        usersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(usersTable);
        add(scrollPane, BorderLayout.CENTER);

        // Top 3 researchers chart
        top3ResearchersData = new DefaultCategoryDataset();
        User[] topResearchers = Util.bubbleSortResearchers(State.users);
        for (int i = 0; i < topResearchers.length; i++) {
            if (i == 3) break;
            if (topResearchers[i] instanceof Researcher researcher) {
                top3ResearchersData.addValue(researcher.getExperimentsCount(), researcher.getCode(), "Experimentos");
            }
        }
        JFreeChart chart = ChartFactory.createBarChart(
                "Top 3 Investigadores",
                "Experimentos",
                "Cantidad",
                top3ResearchersData,
                PlotOrientation.VERTICAL,
                true, true, false
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(250, 500));
        add(chartPanel, BorderLayout.EAST);

        setupButtons();

        setVisible(true);
        revalidate();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == eliminarBtn) {
            if (usersTable.getSelectedRow() != -1) {
                // eliminar de la gráfica
                top3ResearchersData.removeValue(State.users.get(usersTable.getSelectedRow() + 1).getCode(), "Experimentos");
                // eliminar de la lista de usuarios
                State.users.remove(usersTable.getSelectedRow() + 1);
                // eliminar de la tabla
                usersTableModel.removeRow(usersTable.getSelectedRow());
                // actualizar el top 3 de investigadores
                updateTop3ResearchersChart();
                JOptionPane.showMessageDialog(null, "Usuario eliminado con éxito");
            }
        } else if (event.getSource() == agregarBtn) {
            new AddResearcherFrame(usersTableModel);
        } else if (event.getSource() == cargaMasivaBtn) {
            JFileChooser fileChooser = new JFileChooser();
            String path = null;
            int r = fileChooser.showOpenDialog(null);
            if (r == JFileChooser.APPROVE_OPTION) {
                path = fileChooser.getSelectedFile().getAbsolutePath();
            }
            if (path != null) {
                int tmp = State.users.size();
                Util.loadUsers(path);
                for (int i = tmp; i < State.users.size(); i++) {
                    if (State.users.get(i) instanceof Researcher researcher) {
                        usersTableModel.addRow(new String[]{
                                researcher.getCode(),
                                researcher.getName(),
                                String.valueOf(researcher.getGenre()),
                                String.valueOf(researcher.getExperimentsCount())
                        });
                    }
                }
                updateTop3ResearchersChart();
            }

        }
    }

    private void updateTop3ResearchersChart() {
        User[] topResearchers = Util.bubbleSortResearchers(State.users);
        top3ResearchersData.clear();
        for (int i = 0; i < topResearchers.length; i++) {
            if (i == 3) break;
            if (topResearchers[i] instanceof Researcher researcher) {
                top3ResearchersData.addValue(researcher.getExperimentsCount(), researcher.getCode(), "Experimentos");
            }
        }
    }

    private void setupButtons() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Pallete.background);

        agregarBtn = new JButton("Agregar");
        agregarBtn.setForeground(Pallete.background);
        agregarBtn.setBackground(Pallete.primary);
        agregarBtn.setFont(Fonts.normal);
        agregarBtn.addActionListener(this);

        cargaMasivaBtn = new JButton("Carga Masiva");
        cargaMasivaBtn.setForeground(Pallete.background);
        cargaMasivaBtn.setBackground(Pallete.primary);
        cargaMasivaBtn.setFont(Fonts.normal);
        cargaMasivaBtn.addActionListener(this);

        editarBtn = new JButton("Editar");
        editarBtn.setForeground(Pallete.text);
        editarBtn.setBackground(Pallete.secondary);
        editarBtn.setFont(Fonts.normal);
        editarBtn.addActionListener(this);

        eliminarBtn = new JButton("Eliminar");
        eliminarBtn.setForeground(Pallete.text);
        eliminarBtn.setBackground(Pallete.accent);
        eliminarBtn.setFont(Fonts.normal);
        eliminarBtn.addActionListener(this);

        buttonsPanel.add(agregarBtn);
        buttonsPanel.add(cargaMasivaBtn);
        buttonsPanel.add(editarBtn);
        buttonsPanel.add(eliminarBtn);

        add(buttonsPanel, BorderLayout.SOUTH);
    }
}
