package gui;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

public class PasswordEvents implements PEvents {

    private PasswordView pView;

    public PasswordEvents(PasswordView pView) { this.pView = pView; }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "save":
                pView.savePassword();
                break;

            default: break;
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
