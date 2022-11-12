package domain.controller;

import domain.classes.*;
import java.util.*;

public class DomainController {

    private final PasswordList passwords;

    public DomainController() { this.passwords = new PasswordList(); }

    public boolean addPassword(String id, String info) { return this.passwords.addPassword(id, info); }

    public boolean removePassword(String id) { return this.passwords.removePassword(id); }

    public boolean modifyPassword(String currentId, String newId, String newInfo) { return this.passwords.modifyPassword(currentId, newId, newInfo); }

    public ArrayList<Password> getMyPasswords() { return this.passwords.getMyPasswords(); }
}
