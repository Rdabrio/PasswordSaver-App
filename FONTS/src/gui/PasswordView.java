package gui;

import javax.swing.*;
import java.awt.*;

public class PasswordView {

    private final GuiCtrl gui;

    private final JFrame frame = new JFrame("Password View");

    private final JPanel contentPanel = new JPanel();

    private JTextField idField = new JTextField();

    private JTextField infoField = new JTextField();

    private JButton save = new JButton();

    private JLabel idLabel = new JLabel();

    private JLabel infoLabel = new JLabel();

    private String info;

    private String id;

    private final double width;

    private final double height;

    public PasswordView(GuiCtrl gui) {
        this.gui = gui;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.getWidth() / 2.0;
        height = screenSize.getHeight() / 1.7;
    }

    public void init() {
        frame.setContentPane(contentPanel);
        frame.setBounds((int) (width / 1.35), (int) (height / 1.5), (int) (width/2), (int) (height/2));
        frame.setResizable(false);

        initComponents();
    }

    private void initComponents() {
        frame.addWindowListener(gui.getPasswordEvents());
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        contentPanel.setLayout(null);

        idField.setBounds((int) (width / 12), (int) (height / 7.5), (int) (width/2.95), (int) (height/25));
        contentPanel.add(idField);

        infoField.setBounds((int) (width / 12), (int) (height / 4), (int) (width/2.95), (int) (height/25));
        contentPanel.add(infoField);

        save.setBounds((int) (width / 2.8), (int) (height / 2.7), (int) (width/15), (int) (height/27));
        save.setText("Save");
        save.addActionListener(gui.getPasswordEvents());
        save.setActionCommand("save");
        contentPanel.add(save);

        idLabel.setText("ID:");
        idLabel.setBounds((int) (width / 20), (int) (height / -10), (int) (width/2), (int) (height/2));
        contentPanel.add(idLabel);

        infoLabel.setText("Info:");
        infoLabel.setBounds((int) (width / 20), (int) (height / 50), (int) (width/2), (int) (height/2));
        contentPanel.add(infoLabel);
    }

    public void setVisible(boolean b, String id, String info) {
        this.frame.setVisible(b);
        this.id = id;
        this.info = info;
        this.idField.setText(id);
        this.infoField.setText(info);
    }

    public void savePassword() {

    }
}
