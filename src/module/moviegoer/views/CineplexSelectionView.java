package module.moviegoer.views;


import app.Main;
import module.moviegoer.controllers.MovieGoerMgr;
import java.util.Scanner;

public class CineplexSelectionView
{
    private static MovieGoerMgr movieGoerMgr = MovieGoerMgr.getMovieGoerMgr();
    private static Scanner scanner=Main.getSystemScanner();
    public static void run()
    {
        while (true) {
            System.out.println("Cineplex-------------------");
            //List all the cineplexes
            for (int i = 0; i < movieGoerMgr.getCineplexesList().size(); i++) {
                System.out.println((i + 1) + ". " + movieGoerMgr.getCineplexesList().get(i).getCineplexName());
            }
            System.out.println("Please select one of cineplexs above! OR Input 'H' return to previous page");
            String selection = scanner.next();
            if (movieGoerMgr.tryParseInteger(selection)) {
                movieGoerMgr.selectedCineplex(Integer.parseInt(selection)-1);
                CinemaSelectionView.run();
            } else if (selection.equals("H") || selection.equals("h")) {
                return;
            } else {
                System.out.println("ERROR: Wrong input!\n");
            }
        }
    }


}


