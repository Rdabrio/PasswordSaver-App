package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class MainView {

    private GuiCtrl gui;

    private JFrame frame = new JFrame("Main View");

    private JPanel contentPanel = new JPanel();

    private JTable dataTable = new JTable();

    private JButton deleteButton = new JButton();

    private JButton modifyButton = new JButton();

    private JButton addButton = new JButton();

    private final double width;

    private final double height;

    public MainView(GuiCtrl gui) {
        this.gui = gui;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.getWidth() / 2.0;
        height = screenSize.getHeight() / 1.7;
    }

    public void init() {
        frame.setContentPane(contentPanel);

        frame.setBounds((int) (width / 2.1), (int) (height / 2.5), (int) width, (int) height);
        frame.setResizable(false);

        initComponents();
    }

    private void initComponents() {
        frame.addWindowListener(gui.getMainEvents());
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        contentPanel.setLayout(null);

        dataTable.setBounds((int) (width / 3.9), (int) (height / 3.9), (int) (width/2.0), (int) (height/1.4));
        contentPanel.add(dataTable);

        deleteButton.setText("Delete");
        deleteButton.setActionCommand("Delete");
        deleteButton.addActionListener(gui.getMainEvents());
        contentPanel.add(deleteButton);

        addButton.setText("Add");
        addButton.setActionCommand("Add");
        addButton.addActionListener(gui.getMainEvents());
        addButton.setBounds((int) (width / 3.9), (int) (height / 6.0), (int) (width/6.0), (int) (height/9.0));
        contentPanel.add(addButton);

        modifyButton.setText("Modify");
        modifyButton.setActionCommand("Modify");
        modifyButton.addActionListener(gui.getMainEvents());
        contentPanel.add(modifyButton);

    }

    public void setVisible(boolean b) { this.frame.setVisible(b); }

    public void saveAll() { gui.saveAll(); }

    public void close() {
        int code = JOptionPane.showConfirmDialog(null, "Do you want to exit?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (code == 0) {
            gui.saveAll();
            this.frame.dispose();
        }
    }

    public void newPassword() {

    }

    public void modifyPassword() {

    }

    public void deletePassword() {
        int row = dataTable.getSelectedRow();
        if (row >= 0) {
            String id = dataTable.getModel().getValueAt(row, 0).toString();
            if (gui.removePassword(id))JOptionPane.showMessageDialog(null, "Password deleted");
            showPasswords();
        }
        else JOptionPane.showMessageDialog(null, "Select a password to delete it");
    }

    public void showPasswords() {
        ArrayList<String> passwords = gui.getPasswordsData();

        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableModel.addColumn("Password ID");
        tableModel.addColumn("Password Info");
        if (passwords == null) {
            dataTable.setModel(tableModel);
            return;
        }
        for (String s : passwords) {
            String[] parts = s.split("\0");
            tableModel.addRow(parts);
        }
        dataTable.setModel(tableModel);
    }
}
