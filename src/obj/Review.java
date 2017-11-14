package obj;

import app.Main;

import java.util.ArrayList;

/**
 *Review entity class
 * @author Fu, Yunhao
 */
public class Review {

    private double rate;
    private String movieGoerName;
    private String movieName;
    private String comment;

    /**
     * Default constructor
     */
    public Review() {
        rate=0.0d;
        movieGoerName=null;
        movieName=null;
        comment="";
    }

    /**
     * Full constructor
     * @param rate rate
     * @param movieGoerName movie goer name
     * @param movieName movie name
     * @param comment comment
     */
    public Review(double rate, String movieGoerName, String movieName, String comment) {
        this.rate = rate;
        this.movieGoerName = movieGoerName;
        this.movieName = movieName;
        this.comment = comment;
    }


    /**
     * return rate
     * @return rate
     */
    public double getRate() {
        return rate;
    }

    /**
     * return movie goer name
     * @return movie goer name
     */
    public String getMovieGoerName() {
        return movieGoerName;
    }

    /**
     * return movie name
     * @return name
     */
    public String getMovieName() {
        return movieName;
    }

    /**
     * return comment
     * @return comment
     */
    public String getComment() {
        return comment;
    }
}
