package module.admin.views;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class Top5ListView {
    public static void ListTop5Main() throws IOException, ParseException {
        int choice;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("-------------------------------------");
            System.out.println("Top 5 Movie Listing");
            System.out.println("-------------------------------------");
            System.out.println("(1) By Ticket Sales ");
            System.out.println("(2) By Overall Reviewers?");
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
                    //TicketSales.displayTop5();
                    break;

                case 2:
                    //Review.displayTop5();
                    break;
                case 3:
                    return;
            }
        } while (choice < 3);
    } //end of ListTop5Main
}
