package obj;


import java.util.ArrayList;
/**
 * Movie is entity class
 * @author Fu, Yunhao
 */
public class Movie {
    private String movieName;
    private String movieType;
    private String movieStatus;
    private String movieSynopsis;
    private String movieDirector;
    private ArrayList<String> movieCastList;

    /**
     * Default constructor
     */
    public Movie(){
        movieName="";
        movieType="";
        movieStatus="";
        movieSynopsis="";
        movieDirector="";
        movieCastList = new ArrayList<>();
    }

    /**
     * General constructor
     * @param movieName movie name
     * @param movieType movie type
     * @param movieStatus movie status
     * @param movieSynopsis movie synopsis
     * @param movieDirector movie director
     */
    public Movie(String movieName, String movieType, String movieStatus, String movieSynopsis, String movieDirector) {
        this.movieName = movieName;
        this.movieType = movieType;
        this.movieStatus = movieStatus;
        this.movieSynopsis = movieSynopsis;
        this.movieDirector = movieDirector;
        this.movieCastList = new ArrayList<>();
    }

    /**
     *  Full constructor
     * @param movieName movie name
     * @param movieType movie type
     * @param movieStatus movie status
     * @param movieSynopsis movie synopsis
     * @param movieDirector movie director
     * @param movieCast movie cast
     */
    public Movie(String movieName, String movieType, String movieStatus, String movieSynopsis, String movieDirector, ArrayList<String> movieCast) {
        this.movieName = movieName;
        this.movieType = movieType;
        this.movieStatus = movieStatus;
        this.movieSynopsis = movieSynopsis;
        this.movieDirector = movieDirector;
        this.movieCastList = movieCast;
    }

    /**
     * Add cast to movie
     * @param cast String value of cast
     */
    public void addMovieCast(String cast){
            movieCastList.add(cast);
    }

    /**
     * return movie name
     * @return movie name
     */
    public String getMovieName() {
        return movieName;
    }

    /**
     * return movie type
     * @return movie type
     */
    public String getMovieType() {
        return movieType;
    }

    /**
     * return movie status
     * @return movie status
     */
    public String getMovieStatus() {
        return movieStatus;
    }

    /**
     * return movie synopsis
     * @return movie synopsis
     */
    public String getMovieSynopsis() {
        return movieSynopsis;
    }

    /**
     * return movie director
     * @return movie director
     */
    public String getMovieDirector() {
        return movieDirector;
    }

    /**
     * return list of movie cast
     * @return list of movie cast
     */
    public ArrayList<String> getMovieCastList() {
        return movieCastList;
    }

    /**
     * set movie name
     * @param movieName movie name
     */
    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    /**
     * set movie type
     * @param movieType movie type
     */
    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    /**
     * set movie status
     * @param movieStatus movie status
     */
    public void setMovieStatus(String movieStatus) {
        this.movieStatus = movieStatus;
    }

    /**
     * set movie synopsis
     * @param movieSynopsis movie synopsis
     */
    public void setMovieSynopsis(String movieSynopsis) {
        this.movieSynopsis = movieSynopsis;
    }

    /**
     * set movie director
     * @param movieDirector movie director
     */
    public void setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
    }
}
