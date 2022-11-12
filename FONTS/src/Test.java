import domain.classes.*;
import domain.controller.DomainController;
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
                    "\n 1: Add Password\n");
        }

    }
}
