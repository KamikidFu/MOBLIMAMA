package module.moviegoer.views;

import module.moviegoer.controllers.MovieGoerMgr;
import obj.Cinema;

import java.io.IOException;

/**
 * Boundary class of cinema selection
 * @author Fu, Yunhao
 */
public class CinemaSelectionView {
    private static MovieGoerMgr movieGoerMgr = MovieGoerMgr.getMovieGoerMgr();

    /**
     * Run method
     * @throws IOException
     */
    public static void run() throws IOException {
        System.out.println("Cinema---------------------");
        Cinema temp = movieGoerMgr.selectCinema();
        if (temp != null) {
            MovieSelectionView.run();
        } else {
            return;
        }

    }
}