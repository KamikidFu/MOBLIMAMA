package module.admin.views;


import app.Main;
import module.admin.AdminModule;
import module.admin.controllers.StaffMgr;
import obj.TicketPrice;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

/**
 * Boundary class of configuring system
 * @author ANG SHU LIANG
 * @author LEONG MEI HAN
 */
public class SystemConfigView {
    private static Scanner scanner = Main.getSystemScanner();
    private static StaffMgr staffMgr = StaffMgr.getStaffMgr();

    /**
     * Run method, the main method for each boundary classes of UI
     * @throws IOException
     * @throws ParseException
     */
    public static void run() throws IOException, ParseException {
        int choice;
        do {
            System.out.println("-------------------------------------");
            System.out.println("System Configuration");
            System.out.println("-------------------------------------");
            System.out.println("(1) Ticket Price ");
            System.out.println("(2) Set Holidays");
            System.out.println("(3) Home");

            System.out.println("\nPlease select your option: ");
            choice = Main.scannerIntegerInput();

            while (choice > 3 || choice <= 0) {
                System.out.println("You have entered an invalid number");
                System.out.println("Please re-enter your choice: ");
                choice = Main.scannerIntegerInput();
            }

            switch (choice) {
                case 1:
                    EditTicketPrice.run();
                    break;
                case 2:
                    staffMgr.setHoliday();
                    break;
                case 3:
                    return;
            }
        } while (choice < 3);
    } //end of SystemConfigView

}
