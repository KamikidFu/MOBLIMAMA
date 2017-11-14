package module.moviegoer.views;

import app.Main;
import module.moviegoer.controllers.MovieGoerMgr;
import java.io.IOException;
import java.util.Scanner;

/**
 * Boundary class of Ticketing view
 * @author Fu, Yunhao
 */
public class TicketingView {
    private static MovieGoerMgr movieGoerMgr = MovieGoerMgr.getMovieGoerMgr();
    private static Scanner scanner = Main.getSystemScanner();
    //private static BufferedReader bufferedReader = Main.getSystemBufferedReader();
    private static int[] ticketsType = new int[3]; //adult,child,elderly

    public static void run() throws IOException {
        String selection = "";
        //List All Movies & Use Number Codes to order them
        try {
            while (true) {
                System.out.println("\nSelected: " + movieGoerMgr.getSelectedMovieOnScene().getOnSceneMovie().getMovieName() + "------\n(1)Book the Movie\n(2)View Movie Info\n(3)Return");
                selection = scanner.nextLine();
                while (!Main.tryParseInteger(selection)) {
                    System.out.println("Input can not be recognised! Try again please.");
                    selection = scanner.nextLine();
                }
                int i = Integer.parseInt(selection);
                selection = "";
                switch (i) {
                    case 1:
                        System.out.println("How many Adult Tickets?\n");
                        selection = scanner.nextLine();
                        while (!Main.tryParseInteger(selection)) {
                            System.out.println("Input can not be recognised! Try again please.");
                            selection = scanner.nextLine();
                        }
                        ticketsType[0] = Integer.parseInt(selection);
                        selection = "";

                        System.out.println("How many Child Tickets?\n");
                        selection = scanner.nextLine();
                        while (!Main.tryParseInteger(selection)) {
                            System.out.println("Input can not be recognised! Try again please.");
                            selection = scanner.nextLine();
                        }
                        ticketsType[1] = Integer.parseInt(selection);
                        selection = "";

                        System.out.println("How many Elderly Tickets?\n");
                        selection = scanner.nextLine();
                        while (!Main.tryParseInteger(selection)) {
                            System.out.println("Input can not be recognised! Try again please.");
                            selection = scanner.nextLine();
                        }
                        ticketsType[2] = Integer.parseInt(selection);
                        selection = "";

                        movieGoerMgr.setTicketing(ticketsType);
                        SeatSelectionView.run();
                        return;
                    case 2:
                        movieGoerMgr.printMovieInfo();
                        break;
                    case 3:
                        return;
                }
            }
        } catch (Exception e) {
            System.out.println("Input can not be recognised! Try again please.");
        }

    }
}
