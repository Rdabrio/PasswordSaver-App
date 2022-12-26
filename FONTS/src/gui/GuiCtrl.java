package gui;

import domain.controller.DomainController;

import java.util.ArrayList;

public class GuiCtrl {

    private DomainController domain;
    private MainView main;

    public GuiCtrl() {
        this.domain = new DomainController();
        this.main = new MainView(this);
    }

    public void init() {
        domain.iniDomainController();
        main.init();
        main.setVisible(true);
        main.showPasswords();
    }

    public ArrayList<String> getPasswordsData() { return domain.getPasswordsData(); }

    public MainEvents getMainEvents() { return new MainEvents(main); }

    public void saveAll() { domain.saveAll(); }

    public boolean removePassword(String id) { return domain.removePassword(id); }

    public static void main(String[] args) {
        GuiCtrl gui = new GuiCtrl();
        gui.init();
    }
}
