package obj;

import java.util.ArrayList;

/**
 *  Cinema entity class
 *  @author Fu, Yunhao
 */
public class Cinema {
    private String cinemaName;
    private String cinemaClass;
    private String cinemaCode;
    private ArrayList<MovieInCinema> moviesList;
    private int[][] seatMap;

    /**
     *Default constructor
     */
    public Cinema() {
        cinemaName="";
        cinemaClass="";
        cinemaCode="";
        moviesList = new ArrayList<>();
        seatMap = new int[1][1];
    }

    /**
     * General constructor
     * @param cinemaName Cinema name
     * @param cinemaClass Cinema class
     * @param cinemaCode Cinema code
     * @param seatMapLength Cinema seat map length
     * @param searMapWidth Cinema seat map width
     */
    public Cinema(String cinemaName, String cinemaClass, String cinemaCode, int seatMapLength, int searMapWidth) {
        this.cinemaName = cinemaName;
        this.cinemaClass = cinemaClass;
        this.cinemaCode = cinemaCode;
        moviesList=new ArrayList<>();
        seatMap = new int[seatMapLength][searMapWidth];
    }

    /**
     * Full constructor
     * @param cinemaName Cinema name
     * @param cinemaClass Cinema class
     * @param cinemaCode Cinema code
     * @param moviesList List of movies in cinema
     * @param seatMapLength Cinema seat map length
     * @param searMapWidth Cinema seat map width
     */
    public Cinema(String cinemaName, String cinemaClass, String cinemaCode, ArrayList<MovieInCinema> moviesList, int seatMapLength, int searMapWidth) {
        this.cinemaName = cinemaName;
        this.cinemaClass = cinemaClass;
        this.cinemaCode = cinemaCode;
        this.moviesList = moviesList;
        seatMap = new int[seatMapLength][searMapWidth];
    }

    //Methods

    /**
     *  Add movie in cinema
     * @param movie Movie In Cinema
     */
    public void addMovieInCinema(MovieInCinema movie){
        this.moviesList.add(movie);
    }

    //Getters and setters

    /**
     * Return cinema name
     * @return Cinema name
     */
    public String getCinemaName() {
        return cinemaName;
    }

    /**
     * Return cinema class
     * @return Cinema class
     */
    public String getCinemaClass() {
        return cinemaClass;
    }

    /**
     * Return cinema code
     * @return Cinema code
     */
    public String getCinemaCode() {
        return cinemaCode;
    }

    /**
     * Return movies showing in cinema
     * @return List of movies in cinema
     */
    public ArrayList<MovieInCinema> getMoviesList() {
        return moviesList;
    }

    /**
     * Return seat map
     * @return Seat map
     */
    public int[][] getSeatMap() {
        return seatMap;
    }
}
