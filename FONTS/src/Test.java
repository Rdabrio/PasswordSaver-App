import domain.classes.*;
import domain.controller.DomainController;

import java.util.ArrayList;
import java.util.Scanner;

public class Test {

    private static DomainController controller;

    public static void main(String[] args) {

        controller = new DomainController();
        Scanner scan = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Options: \n" +
                    "\t 0: Exit\n" +
                    "\t 1: Add Password\n" +
                    "\t 2: Remove Password\n" +
                    "\t 3: Modify Password\n" +
                    "\t 4: List Passwords\n");

            int code = scan.nextInt();
            scan.nextLine();
            String id, info, newId, newInfo;
            switch (code) {
                case 0:
                    exit = true;
                    break;

                case 1:
                    System.out.println("Password id: ");
                    id = scan.nextLine();
                    System.out.println("Password info: ");
                    info = scan.nextLine();

                    if (controller.addPassword(id, info)) System.out.println("Password added");
                    else System.out.println("Password couldn't be added");
                    break;

                case 2:
                    System.out.println("Password id: ");
                    id = scan.nextLine();

                    if (controller.removePassword(id)) System.out.println("Password removed");
                    else System.out.println("Password couldn't be removed");
                    break;

                case 3:
                    System.out.println("Password id: ");
                    id = scan.nextLine();
                    System.out.println("Password new id: ");
                    newId = scan.nextLine();
                    System.out.println("Password new info: ");
                    newInfo = scan.nextLine();

                    if (controller.modifyPassword(id, newId, newInfo)) System.out.println("Password modified");
                    else System.out.println("Password couldn't be modified");
                    break;

                case 4:
                    System.out.println("Listing all the passwords: ");
                    ArrayList<Password> passwords = controller.getMyPasswords();

                    if (passwords == null) {
                        System.out.println("There aren't passwords yet");
                        break;
                    }

                    for (Password p : passwords) {
                        System.out.println("-ID: " + p.getId() + "    INFO: " + p.getInfo());
                    }
                    break;

                default: break;
            }
        }

    }
}
