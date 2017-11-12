package module.moviegoer.views;


import app.Main;
import module.admin.views.Top5ListView;
import module.moviegoer.controllers.MovieGoerMgr;
import obj.MovieInCinema;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class MovieGoerMainView
{
    private static Scanner scanner = Main.getSystemScanner();
    //private static BufferedReader bufferedReader = Main.getSystemBufferedReader();
    private static MovieGoerMgr movieGoerMgr = MovieGoerMgr.getMovieGoerMgr();
    private static int mainChoice=1;
    public static void run() throws IOException, ParseException {
        String test="";
        while(mainChoice>0 && mainChoice<5) {
            try {
                System.out.println("\nHOME----------\nPlease Select: \n(1)Select Cineplex\n(2)Display all Movies\n(3)Search movie by name\n(4)Show Booking History\n(5)List Top 5 Movies\n(6)Logout");
                test=scanner.nextLine();
                while(!Main.tryParseInteger(test)){
                    System.out.println("Wrong input please check!");
                    test=scanner.nextLine();
                }
                mainChoice = Integer.parseInt(test);
                test = "";
                switch (mainChoice) {
                    case 1:
                        //Select Cineplex
                        CineplexSelectionView.run();
                        break;
                    case 2:
                        //List All Movies
                        MovieInCinema temp;
                        temp = movieGoerMgr.selectMovieInCinema();
                        if(temp!=null) {
                            MovieTimeSelectionView.run();
                            break;
                        }
                        //MovieView
                        break;
                    case 3:
                        if(movieGoerMgr.searchMovieByName()){
                            MovieTimeSelectionView.run();
                        }
                        break;
                    case 4:
                        //Show Booking History
                        movieGoerMgr.printBookingHistory();
                        break;
                    case 5:
                        //List Top 5 Movies
                        Top5ListView.run();
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Input can not be recognised! Try again please.");
                        break;
                }
            }catch (Exception e){
                System.out.println("Input can not be recognised! Try again please.");
                continue;
            }
        }
        return;
    }
}