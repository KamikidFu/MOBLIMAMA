package module.moviegoer.views;


import app.Main;
import module.moviegoer.controllers.MovieGoerMgr;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class SeatSelectionView
{
    private static MovieGoerMgr movieGoerMgr = MovieGoerMgr.getMovieGoerMgr();
    //private static BufferedReader bufferedReader = Main.getSystemBufferedReader();
    private static Scanner scanner = Main.getSystemScanner();

    //Store temp seating arrangement data here in case Moviegoer wishes to redo the selection form

    static int[] ticketinfo = movieGoerMgr.getTicketing();
    public static void run() throws IOException
    {
        String selection="";
        int tixNumber;
        tixNumber = ticketinfo[0]+ticketinfo[1]+ticketinfo[2];

        movieGoerMgr.getSelectedMovieOnScene().printSeat();
        for(int i=1;i<=tixNumber;i++){
            System.out.println("Please input your No."+i+" seat selection: (in format x,y where x is row number and y is column number)");
                selection = scanner.nextLine();
                if (!movieGoerMgr.bookSeat(selection)) {
                    System.out.println("Wrong information input! Please select again!");
                    i--;
                }

        }
        BookingConfirmationView.run();
    }
}
