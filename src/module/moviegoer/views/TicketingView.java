package module.moviegoer.views;

import app.Main;
import module.moviegoer.controllers.MovieGoerMgr;

import java.util.Scanner;

public class TicketingView {
    private static MovieGoerMgr movieGoerMgr = MovieGoerMgr.getMovieGoerMgr();
    static Scanner scanner = Main.getSystemScanner();
    private static int[] ticketsType = new int[3]; //adult,child,elderly

    public void run(){
        int i;

        //List All Movies & Use Number Codes to order them
        System.out.println("(1)Select a Movie\n(2)Movie Info\n");

        i = scanner.nextInt();

        switch(i)
        {
            case 1:
                System.out.println("How many Adult Tickets?\n");
                ticketsType[0] = scanner.nextInt();
                System.out.println("How many Child Tickets?\n");
                ticketsType[1] = scanner.nextInt();
                System.out.println("How many Elderly Tickets?\n");
                ticketsType[2] = scanner.nextInt();
                break;
            case 2:
                //CinemaSelectionView.runView5();
                break;
            default:

        }
    }
}
