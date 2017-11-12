package module.moviegoer.views;


import app.Main;
import module.moviegoer.controllers.MovieGoerMgr;
import obj.Cineplex;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class CineplexSelectionView {
    private static MovieGoerMgr movieGoerMgr = MovieGoerMgr.getMovieGoerMgr();
    private static Scanner scanner = Main.getSystemScanner();

    //private static BufferedReader bufferedReader = Main.getSystemBufferedReader();
    public static void run() throws IOException {
        System.out.println("Cineplex-------------------");
        //List all the cineplexes
        Cineplex temp = movieGoerMgr.selectCineplex();
        if (temp != null) {
            CinemaSelectionView.run();
        } else {
            return;
        }

    }
}


