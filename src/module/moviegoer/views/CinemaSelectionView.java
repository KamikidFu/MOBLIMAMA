package module.moviegoer.views;

import app.Main;
import module.moviegoer.controllers.MovieGoerMgr;
import obj.Cinema;
import obj.Cineplex;

import java.util.Scanner;

public class CinemaSelectionView {
    private static MovieGoerMgr movieGoerMgr = MovieGoerMgr.getMovieGoerMgr();
    private static Scanner scanner= Main.getSystemScanner();

    public static void run(){
        while(true) {
            System.out.println("Cinema---------------------");
            for (int i = 0; i < movieGoerMgr.getCinemasList().size(); i++) {
                System.out.println((i + 1) + ". " + movieGoerMgr.getCinemasList().get(i).getCinemaName());
            }
            System.out.println("Please select one of cineplexs above! OR Input 'H' return to previous page");
            String selection = scanner.next();
            if (movieGoerMgr.tryParseInteger(selection)) {
                movieGoerMgr.selectCinema(Integer.parseInt(selection)-1);
                MovieSelectionView.run();
            } else if (selection.equals("H") || selection.equals("h")) {
                return;
            } else {
                System.out.println("ERROR: Wrong input!\n");
            }
        }
    }
}
