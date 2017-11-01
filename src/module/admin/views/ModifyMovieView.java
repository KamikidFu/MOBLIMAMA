package module.admin.views;


import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import app.Main;
import obj.Movie;
import module.admin.*;

public class ModifyMovieView {
    private static int choice;
    private static Scanner scanner = Main.getSystemScanner();
    public static void run() throws IOException, ParseException {
        Movie movie=new Movie();
        ArrayList<Movie> m1 = new ArrayList<Movie>();
        do {
            System.out.println("-------------------------------------");
            System.out.println("Modify Movie Listing");
            System.out.println("-------------------------------------");
            System.out.println("(1) Create New Movie ");
            System.out.println("(2) Edit existing movie list");
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
                    MovieMethodView.newMovie();
                    break;

                case 2:
                    //Display Current Movie Listing
                    //System.out.println("Enter Movie Number: ");
                    //mNum = scanner.nextInt();
                    //Display Current Movie Details
                    //MovieMethodView.updateMovie(mNum);
                    break;
                case 3:
                    //AdminModule.runAdminHome();
                    break;
            }
        } while (choice < 3);
    } //end of ModifyMovieMain

}
