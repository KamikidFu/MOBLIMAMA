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
        String selection="";
            //List All Movies & Use Number Codes to order them
            try {
                System.out.println("\nSelected: " + movieGoerMgr.getSelectedMovieOnScene().getOnSceneMovie().getMovieName() + "------\n(1)Book the Movie\n(2)View Movie Info\n");
                selection = scanner.nextLine();
                while(!Main.tryParseInteger(selection)){
                    System.out.println("Input can not be recognised! Try again please.");
                    selection = scanner.nextLine();
                }
                int i = Integer.parseInt(selection);
                selection="";
                switch (i) {
                    case 1:
                        System.out.println("How many Student Tickets?\n");
                        selection = scanner.nextLine();
                        while(!Main.tryParseInteger(selection)){
                            System.out.println("Input can not be recognised! Try again please.");
                            selection = scanner.nextLine();
                        }
                        ticketsType[0] = Integer.parseInt(selection);
                        selection="";

                        System.out.println("How many Senior Citizen Tickets?\n");
                        selection = scanner.nextLine();
                        while(!Main.tryParseInteger(selection)){
                            System.out.println("Input can not be recognised! Try again please.");
                            selection = scanner.nextLine();
                        }
                        ticketsType[1] = Integer.parseInt(selection);
                        selection="";

                        System.out.println("How many Standard Tickets?\n");
                        selection = scanner.nextLine();
                        while(!Main.tryParseInteger(selection)){
                            System.out.println("Input can not be recognised! Try again please.");
                            selection = scanner.nextLine();
                        }
                        ticketsType[2] = Integer.parseInt(selection);
                        selection="";

                        movieGoerMgr.setTicketing(ticketsType);
                        SeatSelectionView.run();
                        return;
                    case 2:
                        movieGoerMgr.printMovieInfo();
                        break;
                }
            } catch (Exception e) {
                System.out.println("Input can not be recognised! Try again please.");
            }
    }
}
