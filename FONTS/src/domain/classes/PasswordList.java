package domain.classes;

import java.util.ArrayList;
import java.util.HashMap;

public class PasswordList {

    private HashMap<String, Password> passwords;

    public PasswordList() { this.passwords = new HashMap<>(); }

    public boolean existsPassword(String passwordId) {
        return this.passwords.size() != 0 && this.passwords.containsKey(passwordId);
    }

    public void addPassword(String passwordId, String passwordInfo) throws MyException {
        if (existsPassword(passwordId)) throw new MyException("Password already exists");
        Password p = new Password(passwordId, passwordInfo);
        this.passwords.put(passwordId, p);
    }

    public void removePassword(String passwordId) throws MyException {
        if (!existsPassword(passwordId)) throw new MyException("Password don't exists");
        this.passwords.remove(passwordId);
    }

    public Password getPassword(String passwordId) throws MyException {
        if (!existsPassword(passwordId)) throw new MyException("The password for "+" doesn't exists");
        return this.passwords.get(passwordId);

    }

    public void modifyPassword(String currentId, String newId, String newInfo) throws MyException {
        if (!existsPassword(currentId)) throw new MyException("Password doesn't exists");
        if (existsPassword(newId)) throw new MyException("Password already exists");
        boolean changed = false;
        if (!currentId.equals(newId)) {
            Password p = this.passwords.get(currentId);
            p.setId(newId);
            this.passwords.remove(currentId);
            this.passwords.put(newId, p);
            changed = true;
        }
        if (changed) this.passwords.get(newId).setInfo(newInfo);
        else this.passwords.get(currentId).setInfo(newInfo);
    }

    public ArrayList<Password> getMyPasswords() {
        if (this.passwords.size() == 0) return null;
        return new ArrayList<>(this.passwords.values());
    }

    public ArrayList<String> getPasswordsData() {
        if (this.passwords.size() == 0) return null;

        ArrayList<String> result = new ArrayList<>();
        for (Password p : this.passwords.values()) result.add(p.getId() + "\0" + p.getInfo());
        return result;
    }
}
