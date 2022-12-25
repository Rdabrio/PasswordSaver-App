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
            passwords.addPassword(parts[0], parts[1]);
        }
    }

    public void saveAll() { persistence.saveAll(passwords.getMyPasswords()); }

    public boolean addPassword(String id, String info) { return this.passwords.addPassword(id, info); }

    public boolean removePassword(String id) { return this.passwords.removePassword(id); }

    public boolean modifyPassword(String currentId, String newId, String newInfo) { return this.passwords.modifyPassword(currentId, newId, newInfo); }

    public ArrayList<Password> getMyPasswords() { return this.passwords.getMyPasswords(); }
}
