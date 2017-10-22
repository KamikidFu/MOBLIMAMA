package obj;

public class Movie {
    private String movieName;
    private String movieType;
    private String movieStatus;
    private String movieSynopsis;
    private String movieDirector;
    private String[] movieCast;

    public Movie(){
        movieName="";
        movieType="";
        movieStatus="";
        movieSynopsis="";
        movieDirector="";
        movieCast = new String[2];
    }

    public Movie(String movieName, String movieType, String movieStatus, String movieSynopsis, String movieDirector, String[] movieCast) {
        this.movieName = movieName;
        this.movieType = movieType;
        this.movieStatus = movieStatus;
        this.movieSynopsis = movieSynopsis;
        this.movieDirector = movieDirector;
        this.movieCast = movieCast;
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

    public void setMovieCast(String[] movieCast) {
        this.movieCast = movieCast;
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
