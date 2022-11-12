package domain.classes;

import java.util.ArrayList;
import java.util.HashMap;

public class PasswordList {

    private HashMap<String, Password> passwords;

    public PasswordList() { this.passwords = new HashMap<>(); }

    public boolean existsPassword(String passwordId) {
        return this.passwords.size() != 0 && this.passwords.containsKey(passwordId);
    }

    public boolean addPassword(String passwordId, String passwordInfo) {
        if (existsPassword(passwordId)) return false;
        Password p = new Password(passwordId, passwordInfo);
        this.passwords.put(passwordId, p);
        return true;
    }

    public boolean removePassword(String passwordId) {
        if (!existsPassword(passwordId)) return false;
        this.passwords.remove(passwordId);
        return true;
    }

    public Password getPassword(String passwordId) {
        if (!existsPassword(passwordId)) throw new RuntimeException("The password for "+" doesn't exists");
        return this.passwords.get(passwordId);

    }

    public boolean modifyPassword(String currentId, String newId, String newInfo) {
        if (!existsPassword(currentId)) return false;
        boolean changed = false;
        if (!newId.equals("")) {
            Password p = this.passwords.get(currentId);
            p.setId(newId);
            this.passwords.remove(currentId);
            this.passwords.put(newId, p);
            changed = true;
        }
        if (!newInfo.equals("")) {
            if (changed) this.passwords.get(newId).setInfo(newInfo);
            else this.passwords.get(currentId).setInfo(newInfo);
        }
        return true;
    }

    public ArrayList<Password> getMyPasswords() {
        if (this.passwords.size() == 0) return null;
        return new ArrayList<>(this.passwords.values());
    }
}
