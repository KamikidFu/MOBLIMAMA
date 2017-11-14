package module.moviegoer.views;


import module.moviegoer.controllers.MovieGoerMgr;

import java.io.IOException;

/**
 * Boundary class of booking confirmation
 * @author Fu, Yunhao
 */
public class BookingConfirmationView
{
    private static MovieGoerMgr movieGoerMgr = MovieGoerMgr.getMovieGoerMgr();

    /**
     *  Run method
     * @throws IOException
     */
    public static void run() throws IOException {
        if (movieGoerMgr.printBookingDetails()) {
            System.out.println("Thank you for booking tickets with us!");
            return;
        }
    }
}

