package module.moviegoer.views;


import app.Main;
import module.moviegoer.controllers.MovieGoerMgr;
import obj.Movie;
import obj.MovieGoer;

import java.util.Scanner;

public class MovieGoerMainView
{
    private static Scanner scanner = Main.getSystemScanner();
    private static MovieGoerMgr movieGoerMgr = MovieGoerMgr.getMovieGoerMgr();
    private static int mainChoice=1;
    public static void run()
    {
        while(mainChoice>0 && mainChoice<5) {
            System.out.println("Please Select: \n(1)Select Cineplex\n(2)View Movies\n(3)Show Booking History\n(4)List Top 5 Movies\n(5)Logout\n");

            mainChoice = scanner.nextInt();

            switch (mainChoice) {
                case 1:
                    //Select Cineplex
                    CineplexSelectionView.run();
                    break;
                case 2:
                    //List All Movies
                    Movie temp = movieGoerMgr.selectMovie();
                    //MovieView
                    break;
                case 3:
                    //Show Booking History
                    run();
                    break;
                case 4:
                    //List Top 5 Movies
                    run();
                    break;
            }
        }
        return;
    }
}