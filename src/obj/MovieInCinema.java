package obj;

import java.util.ArrayList;
/**
 * MovieInCinema is a entity class
 * @author Fu, Yunhao
 */
public class MovieInCinema{
    private Movie inCinemaMovie;
    private Cinema inCinemaVenue;
    private ArrayList<CinemaDate> inCinemaShowTimes;

    /**
     * Default constructor
     */
    public MovieInCinema(){
        this.inCinemaMovie =null;
        this.inCinemaVenue =null;
        this.inCinemaShowTimes =new ArrayList<>();
    }

    /**
     * General constructor
     * @param movie movie
     * @param cinema cinema
     */
    public MovieInCinema(Movie movie, Cinema cinema){
        this.inCinemaMovie =movie;
        this.inCinemaVenue =cinema;
        this.inCinemaShowTimes = new ArrayList<>();
    }

    /**
     * Full constructor
     * @param movie movie
     * @param cinema cinema
     * @param showtimes list if show time
     */
    public MovieInCinema(Movie movie, Cinema cinema, ArrayList<CinemaDate> showtimes){
        this.inCinemaMovie =movie;
        this.inCinemaVenue =cinema;
        this.inCinemaShowTimes =showtimes;
    }

    /**
     * Add show time to list
     * @param showtime show time
     */
    public void addShowTime(CinemaDate showtime){
        for(CinemaDate c: inCinemaShowTimes){
            if(c.isSameTime(showtime)) {
                System.out.println("Same showtime detected!");
                return;
            }
        }
        inCinemaShowTimes.add(showtime);
    }

    /**
     * Return movie in cinema
     * @return movie object
     */
    public Movie getInCinemaMovie() {
        return inCinemaMovie;
    }

    /**
     * return venue of cinema
     * @return cinema object
     */
    public Cinema getInCinemaVenue() {
        return inCinemaVenue;
    }

    /**
     * return list of cinema show time
     * @return list of cinema show time
     */
    public ArrayList<CinemaDate> getInCinemaShowTimes() {
        return inCinemaShowTimes;
    }

}
