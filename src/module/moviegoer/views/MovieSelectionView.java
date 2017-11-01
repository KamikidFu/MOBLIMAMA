package module.moviegoer.views;

import app.Main;
import module.moviegoer.controllers.MovieGoerMgr;
import obj.Cinema;

import java.util.Scanner;

public class MovieSelectionView
{
    private static MovieGoerMgr movieGoerMgr = MovieGoerMgr.getMovieGoerMgr();
    static Scanner scanner = Main.getSystemScanner();
    static Cinema  cinema = movieGoerMgr.getSelectedCinema();

    public static void run()//The boolean tells us whether or not we should list out all the movies
    {
        while(true){
            System.out.println("Movie----------------------");
            String oneline="";
            for(int i=0; i<cinema.getMoviesList().size();i++){
                oneline += (cinema.getMoviesList().get(i).getMovieName()+"\t");
                for(int j=0; j<cinema.getMoviesList().get(i).getMovieShowTime().size();j++){
                    oneline+=("|"+i+"."+j+cinema.getMoviesList().get(i).getMovieShowTime().get(j)+"|\t");
                }
                System.out.println(oneline);
                oneline="";
            }
            System.out.println("Please select the movie and show time you want to see :-) OR Input 'H' return to previous page");
            String[] selection = scanner.nextLine().split(".");
            if (selection.length==2) {

            } else if (selection.equals("H") || selection.equals("h")) {
                return;
            } else {
                System.out.println("ERROR: Wrong input!\n");
            }

        }
    }
}