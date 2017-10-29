package module.admin.views;


import module.admin.Admin;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class SystemConfig {

    public static void SystemConfigMain() throws IOException, ParseException {
        int choice;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("-------------------------------------");
            System.out.println("System Configuration");
            System.out.println("-------------------------------------");
            System.out.println("(1) Ticket Price ");
            System.out.println("(2) Set Holidays");
            System.out.println("(3) Home");

            System.out.println("\nPlease select your option: ");
            choice = sc.nextInt();

            while (choice > 3 || choice <= 0) {
                System.out.println("You have entered an invalid number");
                System.out.println("Please re-enter your choice: ");
                choice = sc.nextInt();
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
                    Admin.runAdminHome();
                    break;
            }
        } while (choice < 3);
    } //end of SystemConfig

}
