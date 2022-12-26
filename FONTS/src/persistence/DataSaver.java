package persistence;


import java.io.*;
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

    public void writeData(String[] data) {
        String path = System.getProperty("user.dir") + dataDir;
        try {
            FileWriter writer = new FileWriter(path);
            for (String s : data) {
                writer.write(s + SEPARATOR+"");
            }
            writer.close();
        } catch (IOException e) { e.getStackTrace(); }

    }
}
