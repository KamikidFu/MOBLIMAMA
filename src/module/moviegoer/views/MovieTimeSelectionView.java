package module.moviegoer.views;

import module.moviegoer.controllers.MovieGoerMgr;
import obj.MovieOnScene;

import java.io.IOException;
import java.util.Scanner;
/**
 * Boundary class of movie time selection
 * @author Fu, Yunhao
 */
public class MovieTimeSelectionView
{
    private static MovieGoerMgr movieGoerMgr = MovieGoerMgr.getMovieGoerMgr();

    /**
     * Run method
     * @throws IOException
     */
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
