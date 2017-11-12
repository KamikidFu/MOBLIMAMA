package module.moviegoer.views;

import module.moviegoer.controllers.MovieGoerMgr;
import obj.Cinema;
import obj.MovieInCinema;
import obj.MovieOnScene;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MovieSelectionView
{
    private static MovieGoerMgr movieGoerMgr = MovieGoerMgr.getMovieGoerMgr();
    private static Cinema  cinema = movieGoerMgr.getSelectedCinema();
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    public static void run() throws IOException//The boolean tells us whether or not we should list out all the movies
    {
        System.out.println("Movie---------------------");
        MovieInCinema tempMovieInCinema = movieGoerMgr.selectMovieInCinema();
        if (tempMovieInCinema != null) {
            MovieTimeSelectionView.run();
        } else {
            return;
        }
    }
}