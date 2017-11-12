package module.moviegoer.views;



import app.Main;
import module.moviegoer.controllers.MovieGoerMgr;
import obj.Movie;
import obj.MovieGoer;

import java.io.IOException;
import java.util.Scanner;

public class BookingConfirmationView
{
    private static MovieGoerMgr movieGoerMgr = MovieGoerMgr.getMovieGoerMgr();

    public static void run() throws IOException {
        if (movieGoerMgr.printBookingDetails()) {
            System.out.println("Thank you for booking tickets with us!");
            return;
        }
    }
}

