package module.admin.views;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import app.Main;
import module.admin.controllers.StaffMgr;

public class AdminMainView {

	private static Scanner scanner = Main.getSystemScanner();
	private static StaffMgr staffMgr = StaffMgr.getStaffMgr();
	private static int mainChoice=1;

	public static void run() throws IOException, ParseException {
		String test="";

		while (mainChoice < 5) {
			System.out.println("Please Select:");
			System.out.println("(1) New Movie");
			System.out.println("(2) Modify Movie List");
			System.out.println("(3) List Top 5 Movie Ranking");
			System.out.println("(4) System Configuration");
			System.out.println("(5) Logout");
			test=scanner.nextLine();
			while(!Main.tryParseInteger(test)){
				System.out.println("Wrong input please check!");
				test=scanner.nextLine();
			}
			mainChoice = Integer.parseInt(test);
			test = "";

			while (mainChoice > 5 || mainChoice <= 0) {
				System.out.println("You have entered an invalid number");
				System.out.println("Please re-enter your choice: ");
				test=scanner.nextLine();
				while(!Main.tryParseInteger(test)){
					System.out.println("Wrong input please check!");
					test=scanner.nextLine();
				}
				mainChoice = Integer.parseInt(test);
				test = "";
			}

			switch (mainChoice) {
			case 1:
				staffMgr.addNewMovie();
				break;
			case 2:
				staffMgr.editMovie();
				break;
			case 3:
				//can edit this to link to Moviegoer's List Top 5 Movie Listing -> same view
				Top5ListView.run();
				break;
			case 4:
				SystemConfigView.run();
				break;
			case 5:
				System.out.println("Logged Out");
				break;
			}
		}
		return;
	}//end of AdminMainView.run()


}
