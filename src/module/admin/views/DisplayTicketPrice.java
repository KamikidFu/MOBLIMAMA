package module.admin.views;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import app.Main;
import module.admin.controllers.StaffMgr;
import obj.TicketPrice;

public class DisplayTicketPrice {

	StaffMgr staffMgr = StaffMgr.getStaffMgr();
	
	public static void run() throws IOException, ParseException {
		System.out.println("-------------------------------------");
		System.out.println("Ticket Type\t\t\tDigital Movies\t\t3D Movies");
		System.out.println("Senior Citizens (weekday)\t" + TicketPrice.weekdaySeniorDigital() + "\t\t\t" + "-");
		System.out.println("Student (weekday)\t\t" + TicketPrice.weekdaySeniorDigital() + "\t\t\t" + TicketPrice.weekdayStudentDigital());
		System.out.println("Normal (weekday)\t\t" + TicketPrice.weekdayOthersDigital() + "\t\t\t" + TicketPrice.weekdayOthers3D());
		System.out.println("Weekend and Holidays\t\t" + TicketPrice.weekendEveryoneDigital() + "\t\t\t" + TicketPrice.weekendEveryone3D());
		System.out.println("\n");
	} // end of displayTicketPrice.run()
}
