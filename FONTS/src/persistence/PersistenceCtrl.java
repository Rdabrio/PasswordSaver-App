package persistence;


import domain.classes.Password;

import java.util.ArrayList;

public class PersistenceCtrl {

    private DataSaver dataSaver;
    public PersistenceCtrl() { dataSaver = DataSaver.getInstance(); }

    public String[] restorePasswords() { return dataSaver.readData(); }

    public void saveAll(ArrayList<Password> data) { dataSaver.writeData(data); }
}
