package domain.controller;

import domain.classes.*;
import persistence.PersistenceCtrl;

import java.util.*;

public class DomainController {

    private final PasswordList passwords;
    private PersistenceCtrl persistence;

    public DomainController() {
        this.passwords = new PasswordList();
        this.persistence = new PersistenceCtrl();
    }

    public void iniDomainController() {
        String[] data = persistence.restorePasswords();
        for (int i = 0; i < data.length - 1; ++i) {
            String s = data[i];
            String[] parts = s.split("\0\n");
            try {
                passwords.addPassword(parts[0], parts[1]);
            } catch (MyException e) { System.out.println(e.getMsg()); }
        }
    }

    public void saveAll() {
        String s = "";
        for (Password p : getMyPasswords()) s += p.getId() + "\0\n" + p.getInfo() + "\0\0";
        persistence.saveAll(s.split("\0\0"));
    }

    public void addPassword(String id, String info) throws MyException { this.passwords.addPassword(id, info); }

    public void removePassword(String id) throws MyException { this.passwords.removePassword(id); }

    public void modifyPassword(String currentId, String newId, String newInfo) throws MyException { this.passwords.modifyPassword(currentId, newId, newInfo); }

    public ArrayList<Password> getMyPasswords() { return this.passwords.getMyPasswords(); }

    public ArrayList<String> getPasswordsData() { return this.passwords.getPasswordsData(); }
}
