package gui;

import java.awt.event.*;

public interface Events extends ActionListener, MouseListener, WindowListener {
    public void actionPerformed(ActionEvent e);

    public void mouseClicked(MouseEvent e);

    public void mousePressed(MouseEvent e);

    public void mouseReleased(MouseEvent e);

    public void mouseEntered(MouseEvent e);

    public void mouseExited(MouseEvent e);

    public void windowOpened(WindowEvent e);

    public void windowClosing(WindowEvent e);

    public void windowClosed(WindowEvent e);

    public void windowIconified(WindowEvent e);

    public void windowDeiconified(WindowEvent e);

    public void windowActivated(WindowEvent e);

    public void windowDeactivated(WindowEvent e);
}
