package obj;

import java.util.ArrayList;

public class Cinema {
    private String cinemaName;
    private String cinemaClass;
    private String cinemaCode;
    private ArrayList<Movie> moviesList;

    public Cinema() {
        cinemaName="";
        cinemaClass="";
        cinemaCode="";
        moviesList = new ArrayList<>();
    }

    public Cinema(String cinemaName, String cinemaClass, String cinemaCode) {
        this.cinemaName = cinemaName;
        this.cinemaClass = cinemaClass;
        this.cinemaCode = cinemaCode;
    }

    public Cinema(String cinemaName, String cinemaClass, String cinemaCode, ArrayList<Movie> moviesList) {
        this.cinemaName = cinemaName;
        this.cinemaClass = cinemaClass;
        this.cinemaCode = cinemaCode;
        this.moviesList = moviesList;
    }

    public void addMovie(Movie movie){
        this.moviesList.add(movie);
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public void setCinemaClass(String cinemaClass) {
        this.cinemaClass = cinemaClass;
    }

    public void setCinemaCode(String cinemaCode) {
        this.cinemaCode = cinemaCode;
    }

    public void setMoviesList(ArrayList<Movie> moviesList) {
        this.moviesList = moviesList;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public String getCinemaClass() {
        return cinemaClass;
    }

    public String getCinemaCode() {
        return cinemaCode;
    }

    public ArrayList<Movie> getMoviesList() {
        return moviesList;
    }
}
