package obj;


import java.util.ArrayList;

public class Movie {
    private String movieName;
    private String movieType;
    private String movieStatus;
    private String movieSynopsis;
    private String movieDirector;
    private ArrayList<String> movieCastList;

    public Movie(){
        movieName="";
        movieType="";
        movieStatus="";
        movieSynopsis="";
        movieDirector="";
        movieCastList = new ArrayList<>();
    }

    public Movie(String movieName, String movieType, String movieStatus, String movieSynopsis, String movieDirector) {
        this.movieName = movieName;
        this.movieType = movieType;
        this.movieStatus = movieStatus;
        this.movieSynopsis = movieSynopsis;
        this.movieDirector = movieDirector;
        this.movieCastList = new ArrayList<>();
    }

    public Movie(String movieName, String movieType, String movieStatus, String movieSynopsis, String movieDirector, ArrayList<String> movieCast) {
        this.movieName = movieName;
        this.movieType = movieType;
        this.movieStatus = movieStatus;
        this.movieSynopsis = movieSynopsis;
        this.movieDirector = movieDirector;
        this.movieCastList = movieCast;
    }


    public void addMovieCast(String cast){
            movieCastList.add(cast);
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

    public ArrayList<String> getMovieCastList() {
        return movieCastList;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    public void setMovieStatus(String movieStatus) {
        this.movieStatus = movieStatus;
    }

    public void setMovieSynopsis(String movieSynopsis) {
        this.movieSynopsis = movieSynopsis;
    }

    public void setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
    }

    public void newMovieCastList() {
        this.movieCastList = new ArrayList<>();
    }
}
