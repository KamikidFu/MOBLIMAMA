package obj;

import java.util.ArrayList;

public class MovieInCinema{
    private Movie inCinemaMovie;
    private Cinema inCinemaVenue;
    private ArrayList<CinemaDate> inCinemaShowTimes;

    public MovieInCinema(){
        this.inCinemaMovie =null;
        this.inCinemaVenue =null;
        this.inCinemaShowTimes =new ArrayList<>();
    }

    public MovieInCinema(Movie movie, Cinema cinema){
        this.inCinemaMovie =movie;
        this.inCinemaVenue =cinema;
        this.inCinemaShowTimes = new ArrayList<>();
    }

    public MovieInCinema(Movie movie, Cinema cinema, ArrayList<CinemaDate> showtimes){
        this.inCinemaMovie =movie;
        this.inCinemaVenue =cinema;
        this.inCinemaShowTimes =showtimes;
    }

    public void addShowTime(CinemaDate showtime){
        for(CinemaDate c: inCinemaShowTimes){
            if(c.isSameTime(showtime)) {
                System.out.println("There is a same showtime detected!");
                return;
            }
        }
        inCinemaShowTimes.add(showtime);
    }

    public Movie getInCinemaMovie() {
        return inCinemaMovie;
    }

    public Cinema getInCinemaVenue() {
        return inCinemaVenue;
    }

    public ArrayList<CinemaDate> getInCinemaShowTimes() {
        return inCinemaShowTimes;
    }

    public void setInCinemaMovie(Movie inCinemaMovie) {
        this.inCinemaMovie = inCinemaMovie;
    }

    public void setInCinemaVenue(Cinema inCinemaVenue) {
        this.inCinemaVenue = inCinemaVenue;
    }
}
