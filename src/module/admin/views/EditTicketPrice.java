package module.admin.views;

import java.util.Scanner;
import java.io.IOException;
import java.text.ParseException;
import app.Main;
import module.admin.controllers.StaffMgr;
import obj.TicketPrice;

public class EditTicketPrice {

	private static Scanner scanner = Main.getSystemScanner();
	StaffMgr staffMgr = StaffMgr.getStaffMgr();
	private static int mainChoice=1;
	
	public static void run() throws IOException, ParseException {
		
		DisplayTicketPrice.run();

		if (mainChoice < 3) {
			System.out.println("Please Select:");
			System.out.println("(1) Change base price");
			System.out.println("(2) Home");
			mainChoice = Main.scannerIntegerInput();

			while (mainChoice > 2 || mainChoice <= 0) {
				System.out.println("You have entered an invalid number");
				System.out.println("Please re-enter your choice: ");
				mainChoice = Main.scannerIntegerInput();
			}

			switch (mainChoice) {
			case 1:
				System.out.println("Current base price is $" + TicketPrice.getBasePrice() + ".");
				System.out.println("Enter new base price: ");
				double bp = Main.scannerIntegerInput();
				TicketPrice.setBasePrice(bp);
				System.out.println("\nPrice updated! New pricing:");
				DisplayTicketPrice.run();
				break;
			case 2:
				break;
			}
		}
		return;
	}//end of EditTicketPrice.run()
}