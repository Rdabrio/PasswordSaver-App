package persistence;

import domain.classes.Password;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DataSaver {

    private static DataSaver dataSaverSingleton;
    private char SEPARATOR = (char)27;
    private String dataDir = File.separator + "DATA" + File.separator + "password.info";

    private DataSaver() {}

    public static DataSaver getInstance() {
        if (dataSaverSingleton == null) dataSaverSingleton = new DataSaver();
        return dataSaverSingleton;
    }

    public String read(String path) {
        String result = "";

        try {
            FileReader file = new FileReader(path);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) result += reader.nextLine() + "\n";
            reader.close();
        } catch (FileNotFoundException e) { e.printStackTrace(); }
        return result;
    }

    public String[] readData() {
        String path = System.getProperty("user.dir") + dataDir;
        String result = read(path);
        return result.split(String.valueOf(SEPARATOR));
    }

    public void writeData(ArrayList<Password> data) {
        String path = System.getProperty("user.dir") + dataDir;
        try {
            FileWriter writer = new FileWriter(path);
            for (Password p : data) {
                writer.write(p.getId() + "\0\n" + p.getInfo() + SEPARATOR+"");
            }
            writer.close();
        } catch (IOException e) { e.getStackTrace(); }

    }
}
