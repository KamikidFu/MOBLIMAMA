package obj;

import java.util.ArrayList;

public class Cineplex {
    private String cineplexName;
    private String cineplexLocation;
    private ArrayList<Cinema> cinemaList;

    public Cineplex() {
        cinemaList=new ArrayList<>();
        cineplexLocation="";
        cineplexName="";
    }

    public Cineplex(String cineplexName, String cineplexLocation) {
        this.cineplexName = cineplexName;
        this.cineplexLocation = cineplexLocation;
    }

    public Cineplex(String cineplexName, String cineplexLocation, ArrayList<Cinema> cinemaList) {
        this.cineplexName = cineplexName;
        this.cineplexLocation = cineplexLocation;
        this.cinemaList = cinemaList;
    }

    public String getCineplexName() {
        return cineplexName;
    }

    public void setCineplexName(String cineplexName) {
        this.cineplexName = cineplexName;
    }

    public String getCineplexLocation() {
        return cineplexLocation;
    }

    public void setCineplexLocation(String cineplexLocation) {
        this.cineplexLocation = cineplexLocation;
    }

    public ArrayList<Cinema> getCinemaList() {
        return cinemaList;
    }

    public void setCinemaList(ArrayList<Cinema> cinemaList) {
        this.cinemaList = cinemaList;
    }
}
