package obj;

import java.util.ArrayList;

public class Movie {
    private String movieName;
    private String movieType;
    private String movieStatus;
    private String movieSynopsis;
    private String movieDirector;
    private String[] movieCast;
    private ArrayList<CinemaDate> movieShowTime;

    public Movie(){
        movieName="";
        movieType="";
        movieStatus="";
        movieSynopsis="";
        movieDirector="";
        movieCast = new String[2];
        movieShowTime=new ArrayList<>();
    }

    public Movie(String movieName, String movieType, String movieStatus, String movieSynopsis, String movieDirector, String[] movieCast) {
        this.movieName = movieName;
        this.movieType = movieType;
        this.movieStatus = movieStatus;
        this.movieSynopsis = movieSynopsis;
        this.movieDirector = movieDirector;
        this.movieCast = movieCast;
        this.movieShowTime=new ArrayList<>();
    }

    public void addShowTime(CinemaDate showtime){
        movieShowTime.add(showtime);
    }

    public ArrayList<CinemaDate> getMovieShowTime() {
        return movieShowTime;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getMovieType() {
        return movieType;
    }

    public String getMovieStatus() {
        return movieStatus;
    }

    public String getMovieSynopsis() {
        return movieSynopsis;
    }

    public String getMovieDirector() {
        return movieDirector;
    }

    public String[] getMovieCast() {
        return movieCast;
    }
}
