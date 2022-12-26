package persistence;


public class PersistenceCtrl {

    private DataSaver dataSaver;
    public PersistenceCtrl() { dataSaver = DataSaver.getInstance(); }

    public String[] restorePasswords() { return dataSaver.readData(); }

    public void saveAll(String[] data) { dataSaver.writeData(data); }
}
