package gui;

import javax.swing.*;
import java.awt.*;

public class PasswordView {

    private final GuiCtrl gui;

    private final JFrame frame = new JFrame("Main View");

    private final JPanel contentPanel = new JPanel();

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
        frame.setBounds((int) (width / 2.1), (int) (height / 2.5), (int) width, (int) height);
        frame.setResizable(false);

        initComponents();
    }

    private void initComponents() {
        frame.addWindowListener(gui.getPasswordEvents());
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        contentPanel.setLayout(null);
    }

    public void setVisible(boolean b) { this.frame.setVisible(b); }
}
