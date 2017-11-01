package module.admin.views;


import app.Main;
import module.admin.AdminModule;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class SystemConfigView {
    private static Scanner scanner = Main.getSystemScanner();
    public static void SystemConfigMain() throws IOException, ParseException {
        int choice;
        do {
            System.out.println("-------------------------------------");
            System.out.println("System Configuration");
            System.out.println("-------------------------------------");
            System.out.println("(1) Ticket Price ");
            System.out.println("(2) Set Holidays");
            System.out.println("(3) Home");

            System.out.println("\nPlease select your option: ");
            choice = scanner.nextInt();

            while (choice > 3 || choice <= 0) {
                System.out.println("You have entered an invalid number");
                System.out.println("Please re-enter your choice: ");
                choice = scanner.nextInt();
            }

            switch (choice) {
                case 1:
                    //Price.displayPrice(); create price object
                    //displayPrice method prints out pricing table
                    break;

                case 2:
                    //Holidays.setHolidays(); create holiday object
                    break;
                case 3:
                    //AdminModule.runAdminHome();
                    break;
            }
        } while (choice < 3);
    } //end of SystemConfigView

}
