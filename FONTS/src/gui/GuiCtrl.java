package gui;

import domain.classes.MyException;
import domain.controller.DomainController;

import java.util.ArrayList;

public class GuiCtrl {

    private DomainController domain;
    private MainView main;

    private PasswordView pView;

    public GuiCtrl() {
        this.domain = new DomainController();
        this.main = new MainView(this);
        this.pView = new PasswordView(this);
    }

    public void init() {
        domain.iniDomainController();
        main.init();
        main.setVisible(true);
        main.showPasswords();
        pView.init();
    }

    public ArrayList<String> getPasswordsData() { return domain.getPasswordsData(); }

    public MainEvents getMainEvents() { return new MainEvents(main); }

    public PasswordEvents getPasswordEvents() { return new PasswordEvents(pView); }

    public void setVisiblePasswordView(boolean b, String id, String info) { pView.setVisible(b, id, info); }

    public void saveAll() { domain.saveAll(); }

    public void removePassword(String id) throws MyException { domain.removePassword(id); }

    public void modifyPassword(String oID, String ID, String info) throws MyException { domain.modifyPassword(oID, ID, info); }

    public void showPasswords() { main.showPasswords(); }

    public void addPassword(String id, String info) throws MyException { domain.addPassword(id, info);}

    public static void main(String[] args) {
        GuiCtrl gui = new GuiCtrl();
        gui.init();
    }
}
