package gui;

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

    public void setVisiblePasswordView(boolean b) { pView.setVisible(b); }

    public void saveAll() { domain.saveAll(); }

    public boolean removePassword(String id) { return domain.removePassword(id); }

    public static void main(String[] args) {
        GuiCtrl gui = new GuiCtrl();
        gui.init();
    }
}
