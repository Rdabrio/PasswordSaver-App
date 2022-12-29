package gui;

import domain.classes.MyException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class MainView {

    private final GuiCtrl gui;

    private final JFrame frame = new JFrame("Main View");

    private final JPanel contentPanel = new JPanel(new GridLayout(7, 3));

    private final JPanel mainPanel = new JPanel(new BorderLayout());

    private final JTable dataTable = new JTable();

    private final JButton deleteButton = new JButton();

    private final JButton modifyButton = new JButton();

    private final JButton addButton = new JButton();

    private final double width;

    private final double height;

    public MainView(GuiCtrl gui) {
        this.gui = gui;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.getWidth() / 2.0;
        height = screenSize.getHeight() / 1.7;
    }

    public void init() {
        frame.setContentPane(mainPanel);
        frame.setBounds((int) (width / 2.1), (int) (height / 2.5), (int) width, (int) height);
        frame.setResizable(false);
        frame.setMinimumSize(new Dimension((int) width, (int) height));
        frame.setMaximumSize(new Dimension((int) width, (int) height));

        initComponents();
    }

    private void initComponents() {
        frame.addWindowListener(gui.getMainEvents());
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        JScrollPane scrollPane = new JScrollPane(dataTable);

        deleteButton.setText("Delete");
        deleteButton.setActionCommand("Delete");
        deleteButton.addActionListener(gui.getMainEvents());
        deleteButton.setBounds((int) (width / 1.25), (int) (height / 1.4), (int) (width/7.0), (int) (height/19.0));

        addButton.setText("Add");
        addButton.setActionCommand("Add");
        addButton.addActionListener(gui.getMainEvents());
        addButton.setBounds((int) (width / 1.25), (int) (height / 2.7), (int) (width/7.0), (int) (height/19.0));

        modifyButton.setText("Modify");
        modifyButton.setActionCommand("Modify");
        modifyButton.addActionListener(gui.getMainEvents());
        modifyButton.setBounds((int) (width / 1.25), (int) (height / 1.85), (int) (width/7.0), (int) (height/19.0));

        for (int i = 1; i <= 21; ++i) {
            if (i == 5) contentPanel.add(addButton);
            else if (i == 11) contentPanel.add(modifyButton);
            else if ( i == 17) contentPanel.add(deleteButton);
            else contentPanel.add(new JPanel());
        }

        mainPanel.add(scrollPane, BorderLayout.WEST);
        mainPanel.add(contentPanel);

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
            try {
                gui.removePassword(id);
                showPasswords();
                JOptionPane.showMessageDialog(null, "Password deleted");
            } catch (MyException e) { JOptionPane.showMessageDialog(null, e.getMsg()); }
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
