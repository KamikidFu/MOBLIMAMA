package module.admin.controllers;

import app.Main;
import obj.*;

import java.util.ArrayList;
import java.util.Scanner;

public class StaffMgr  {
    private static StaffMgr staffMgr;
    private static IUser staff;
    private static ArrayList<Cinema> cinemasList = Main.getCinemasList();
    private static ArrayList<Cineplex> cineplexesList = Main.getCineplexesList();
    private static ArrayList<Review> reviewsList = Main.getReviewsList();
    private static ArrayList<Movie> moviesList = Main.getMoviesList();
    private static Scanner systemScanner = Main.getSystemScanner();

    private StaffMgr(){
    }

    static {
        staffMgr = new StaffMgr();
    }

    public static StaffMgr getStaffMgr() {
        return staffMgr;
    }
    public static void activateStaffMgr(IUser Staff){staff = Staff;}
}
