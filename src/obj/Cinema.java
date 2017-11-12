package obj;

import java.util.ArrayList;

public class Cinema {
    private String cinemaName;
    private String cinemaClass;
    private String cinemaCode;
    private ArrayList<MovieInCinema> moviesList;
    private int[][] seatMap;


    public Cinema() {
        cinemaName="";
        cinemaClass="";
        cinemaCode="";
        moviesList = new ArrayList<>();
        seatMap = new int[1][1];
    }

    public Cinema(String cinemaName, String cinemaClass, String cinemaCode, int seatMapLength, int searMapWidth) {
        this.cinemaName = cinemaName;
        this.cinemaClass = cinemaClass;
        this.cinemaCode = cinemaCode;
        moviesList=new ArrayList<>();
        seatMap = new int[seatMapLength][searMapWidth];
    }

    public Cinema(String cinemaName, String cinemaClass, String cinemaCode, ArrayList<MovieInCinema> moviesList, int seatMapLength, int searMapWidth) {
        this.cinemaName = cinemaName;
        this.cinemaClass = cinemaClass;
        this.cinemaCode = cinemaCode;
        this.moviesList = moviesList;
        moviesList=new ArrayList<>();
        seatMap = new int[seatMapLength][searMapWidth];
    }

    //Methods
    public void addMovieInCinema(MovieInCinema movie){
        this.moviesList.add(movie);
    }




    //Getters and setters
    public String getCinemaName() {
        return cinemaName;
    }

    public String getCinemaClass() {
        return cinemaClass;
    }

    public String getCinemaCode() {
        return cinemaCode;
    }

    public ArrayList<MovieInCinema> getMoviesList() {
        return moviesList;
    }

    public int[][] getSeatMap() {
        return seatMap;
    }
}
