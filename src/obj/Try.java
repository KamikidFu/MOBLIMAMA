package obj;

import java.util.Scanner;

public class Try {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TicketPrice tp = new TicketPrice();
		System.out.print(tp.weekdaySeniorDigital()); // seniors can't tolerate the excitement of 3D movies
		System.out.println("\n");
		System.out.print(tp.weekdayStudentDigital()); // student
		System.out.println("\n");
		System.out.print(tp.weekdayStudent3D()); // student
		System.out.println("\n");
		System.out.print(tp.weekdayOthersDigital()); // others
		System.out.println("\n");
		System.out.print(tp.weekdayOthers3D()); // others
		System.out.println("\n");
		System.out.print(tp.weekendEveryoneDigital()); // weekend + holiday where they rip off your money
		System.out.println("\n");
		System.out.print(tp.weekendEveryone3D());
		System.out.println("\n");
		System.out.println("Set new base price: ");
		Scanner sc = new Scanner(System.in);
		double basePrice = sc.nextDouble();
		tp.setBasePrice(basePrice);
		
		System.out.println("New prices:\n");
		System.out.print(tp.weekdaySeniorDigital()); // seniors can't tolerate the excitement of 3D movies
		System.out.println("\n");
		System.out.print(tp.weekdayStudentDigital()); // student
		System.out.println("\n");
		System.out.print(tp.weekdayStudent3D()); // student
		System.out.println("\n");
		System.out.print(tp.weekdayOthersDigital()); // others
		System.out.println("\n");
		System.out.print(tp.weekdayOthers3D()); // others
		System.out.println("\n");
		System.out.print(tp.weekendEveryoneDigital()); // weekend + holiday where they rip off your money
		System.out.println("\n");
		System.out.print(tp.weekendEveryone3D());
		System.out.println("\n");
	}

}
