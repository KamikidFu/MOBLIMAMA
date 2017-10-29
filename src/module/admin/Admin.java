package module.admin;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import obj.Movie;
import module.admin.views.ModifyMovie;

public class Admin {
    public static void runAdminHome() throws IOException, ParseException {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("-------------------------------------");
            System.out.println("Welcome Staff!");
            System.out.println("-------------------------------------");
            System.out.println("(1) Modify Movie List");
            System.out.println("(2) List Top 5 Movie Ranking");
            System.out.println("(3) System Configuration\n");
            System.out.println("(4) Terminate\n");
            System.out.println("Enter the your choice: ");
            choice = sc.nextInt();

            while (choice > 4 || choice <= 0) {
                System.out.println("You have entered an invalid number");
                System.out.println("Please re-enter your choice: ");
                choice = sc.nextInt();
            }

            switch (choice) {
                case 1:
                    ModifyMovie.ModifyMovieMain();
                    break;
                case 2:
                    //can edit this to link to Moviegoer's List Top 5 Movie Listing -> same view
                    ListTop5Main();
                    break;
                case 3:
                    //SystemConfig.SystemConfigMain();
                    break;
                case 4:
                    System.out.println("Terminating");
                    break;
            }

        } while (choice < 4);


    }//end of runAdminHome


    public static void ListTop5Main() throws IOException, ParseException {
        int choice;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("-------------------------------------");
            System.out.println("Top 5 Movie Listing");
            System.out.println("-------------------------------------");
            System.out.println("(1) By Ticket Sales ");
            System.out.println("(2) By Overall Reviewers?Rating");
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
                    runAdminHome();
                    break;
            }
        } while (choice < 3);
    } //end of ListTop5Main
}
