package module.moviegoer.controllers;

import app.Main;
import com.sun.org.apache.bcel.internal.generic.IUSHR;
import obj.*;

import java.util.ArrayList;
import java.util.Scanner;

public class MovieGoerMgr {
    private static Scanner systemScanner = Main.getSystemScanner();
    private static MovieGoerMgr movieGoerMgr;
    private static IUser movieGoer;

    private  ArrayList<Cinema> cinemasList = Main.getCinemasList();
    private  ArrayList<Cineplex> cineplexesList = Main.getCineplexesList();
    private  ArrayList<Review> reviewsList = Main.getReviewsList();
    private  ArrayList<Booking> bookingsList = Main.getBookingsList();
    private  ArrayList<Movie> moviesList = Main.getMoviesList();

    private  Cineplex selectedCineplex;
    private  Cinema selectedCinema;
    private  Movie selectedMovie;
    private  Booking newBooking;



    private MovieGoerMgr(){}
    static {
        movieGoerMgr = new MovieGoerMgr();
    }

    public static MovieGoerMgr getMovieGoerMgr(){
        if(movieGoer==null) {
            System.err.println("Manager has no user value");
            return null;
        }
        return movieGoerMgr;
    }

    public static void activateMovieGoerMgr(IUser MovieGoer){movieGoer = MovieGoer;}

    //Methods interfaces
    public Movie selectMovie(){
        int counter = 1;
        for(Movie m:moviesList){
            System.out.println(counter+m.getMovieName()+"\t"+m.getMovieType()+"\t"+m.getMovieStatus());
            counter++;
        }
        System.out.println("Please select the movie you want to see :-)");
        int selection = systemScanner.nextInt();
        selectedMovie = moviesList.get(selection);
        return moviesList.get(selection);
    }

    public void selectedCineplex(int cineplexIndex){
        this.selectedCineplex = cineplexesList.get(cineplexIndex);
    }

    public void selectCinema(int cinemaIndex) {
        this.selectedCinema = cinemasList.get(cinemaIndex);
    }

    public boolean tryParseInteger(String string){
        try{
            Integer.parseInt(string);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    //Getters
    public static IUser getMovieGoer() {
        return movieGoer;
    }

    public  ArrayList<Cinema> getCinemasList() {
        return cinemasList;
    }

    public  ArrayList<Cineplex> getCineplexesList() {
        return cineplexesList;
    }

    public  ArrayList<Review> getReviewsList() {
        return reviewsList;
    }

    public  ArrayList<Booking> getBookingsList() {
        return bookingsList;
    }

    public  ArrayList<Movie> getMoviesList() {
        return moviesList;
    }

    public Cineplex getSelectedCineplex() {
        return selectedCineplex;
    }

    public Cinema getSelectedCinema() {
        return selectedCinema;
    }

    public Movie getSelectedMovie() {
        return selectedMovie;
    }

    public Booking getNewBooking() {
        return newBooking;
    }

}
