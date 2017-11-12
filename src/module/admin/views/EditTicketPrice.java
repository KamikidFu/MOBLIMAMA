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
			System.out.println("(2) Don't change");
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
				DisplayTicketPrice.run();
				break;
			case 2:
				break;
			}
		}
		return;
	}//end of EditTicketPrice.run()
}


/*//TicketPrice.displayPrice(); //create price object
System.out.println("Current base price is $" + TicketPrice.getBasePrice() + ".");
System.out.println("1) Change base price");
System.out.println("2) Don't change");
int change = scanner.nextInt();
if (change == 1) {
	System.out.println("Enter the new base price: ");
	double b = scanner.nextInt();
	//TicketPrice.setBasePrice(b);
}
//TicketPrice.displayNewPrice();
//TicketPrice.printDialog();
//displayPrice method prints out pricing table*/