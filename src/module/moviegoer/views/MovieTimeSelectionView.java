package module.moviegoer.views;

import module.moviegoer.controllers.MovieGoerMgr;
import obj.MovieOnScene;

import java.io.IOException;
import java.util.Scanner;

public class MovieTimeSelectionView
{
    private static MovieGoerMgr movieGoerMgr = MovieGoerMgr.getMovieGoerMgr();
    public static  void run() throws IOException
    {
            //Lists Showtimes
            System.out.println("Select a timing: \n");

            MovieOnScene tempMovieOnScene = movieGoerMgr.selectMovieOnScene();
            if (tempMovieOnScene != null) {
                TicketingView.run();
            } else {
                return;
            }

    }
}
