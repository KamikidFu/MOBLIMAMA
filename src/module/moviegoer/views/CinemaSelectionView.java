package module.moviegoer.views;

import app.Main;
import module.moviegoer.controllers.MovieGoerMgr;
import obj.Cinema;
import obj.Cineplex;

import java.io.IOException;
import java.util.Scanner;

public class CinemaSelectionView {
    private static MovieGoerMgr movieGoerMgr = MovieGoerMgr.getMovieGoerMgr();

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