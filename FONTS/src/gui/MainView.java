package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class MainView {

    private final GuiCtrl gui;

    private final JFrame frame = new JFrame("Main View");

    private final JPanel contentPanel = new JPanel();

    private final JTable dataTable = new JTable();

    private final JButton deleteButton = new JButton();

    private final JButton modifyButton = new JButton();

    private final JButton addButton = new JButton();

    private final JLabel IDLabel = new JLabel();

    private final JLabel InfoLabel = new JLabel();

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

        IDLabel.setText("Password ID");
        IDLabel.setBounds((int) (width / 3), (int) (height / 4.7), (int) (width/7), (int) (height/19));
        contentPanel.add(IDLabel);

        InfoLabel.setText("Password Info");
        InfoLabel.setBounds((int) (width / 1.7), (int) (height / 4.7), (int) (width/7), (int) (height/19));
        contentPanel.add(InfoLabel);

        deleteButton.setText("Delete");
        deleteButton.setActionCommand("Delete");
        deleteButton.addActionListener(gui.getMainEvents());
        deleteButton.setBounds((int) (width / 1.25), (int) (height / 1.4), (int) (width/7.0), (int) (height/19.0));
        contentPanel.add(deleteButton);

        addButton.setText("Add");
        addButton.setActionCommand("Add");
        addButton.addActionListener(gui.getMainEvents());
        addButton.setBounds((int) (width / 1.25), (int) (height / 2.7), (int) (width/7.0), (int) (height/19.0));
        contentPanel.add(addButton);

        modifyButton.setText("Modify");
        modifyButton.setActionCommand("Modify");
        modifyButton.addActionListener(gui.getMainEvents());
        modifyButton.setBounds((int) (width / 1.25), (int) (height / 1.85), (int) (width/7.0), (int) (height/19.0));
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
        gui.setVisiblePasswordView(true, "", "");
    }

    public void modifyPassword() {
        int row = dataTable.getSelectedRow();
        if (row >= 0) {
            String id = dataTable.getModel().getValueAt(row, 0).toString();
            String info = dataTable.getModel().getValueAt(row, 1).toString();
            gui.setVisiblePasswordView(true, id, info);
        }
        else JOptionPane.showMessageDialog(null, "Select a password to modify");

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
