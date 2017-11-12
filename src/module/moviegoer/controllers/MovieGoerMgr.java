package module.moviegoer.controllers;

import app.Main;
import obj.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.SimpleFormatter;

public class MovieGoerMgr {
    private static Scanner scanner = Main.getSystemScanner();
    //private static BufferedReader bufferedReader = Main.getSystemBufferedReader();
    private static MovieGoerMgr movieGoerMgr;
    private static IUser movieGoer;


    private  ArrayList<Cineplex> cineplexesList = Main.getCineplexesList();
    private  ArrayList<Review> reviewsList = Main.getReviewsList();
    private  ArrayList<Booking> bookingsList = Main.getBookingsList();
    private ArrayList<String> tempBookedSeat = new ArrayList<>();

    private  Cineplex selectedCineplex;
    private  Cinema selectedCinema;
    private MovieInCinema selectedMovieInCinema;
    private  Booking newBooking;
    private MovieOnScene selectedMovieOnScene;
    private int[] ticketing;
    private double finalPrice;


    private MovieGoerMgr(){}
    static {
        movieGoerMgr = new MovieGoerMgr();
    }

    public static MovieGoerMgr getMovieGoerMgr(){
        if(movieGoer==null) {
            System.err.println("Manager has no user value");
            return null;
        }
        return movieGoerMgr;
    }

    public static void activateMovieGoerMgr(IUser MovieGoer){movieGoer = MovieGoer;}



    //Methods interfaces
    public Cineplex selectCineplex() throws IOException {
        String selection=null;
        while(true) {
            int counter = 1;
            for (Cineplex c : cineplexesList) {
                System.out.println(counter + "." + c.getCineplexName());
                counter++;
            }
            System.out.println("Please select one of cineplexs above! Or input R to return previous page.");

            while(scanner.hasNext()) {
                selection = scanner.nextLine();

                if (Main.tryParseInteger(selection)) {
                    int temp = Integer.parseInt(selection) - 1;
                    if (temp < cineplexesList.size()) {
                        this.selectedCineplex = cineplexesList.get(temp);
                        return this.selectedCineplex;
                    } else {
                        System.out.println("There is no cineplex you selected! Please try again!");
                        continue;
                    }
                } else if (selection.equals("R")) {
                    return null;
                } else {
                    System.out.println("Input cannot be recognised! Please try again!");
                }
            }
        }
    }


    public Cinema selectCinema() throws IOException {
        while(true) {
            int counter = 1;
            for (Cinema c : selectedCineplex.getCinemaList()) {
                System.out.println(counter + "." + c.getCinemaName());
                counter++;
            }
            System.out.println("Please select one of cinema above! Or input R to return previous page.");
            String selection =scanner.nextLine();
            if (Main.tryParseInteger(selection)) {
                int temp = Integer.parseInt(selection) - 1;
                if(temp<this.selectedCineplex.getCinemaList().size()) {
                    this.selectedCinema = selectedCineplex.getCinemaList().get(temp);
                    return this.selectedCinema;
                }else {
                    System.out.println("There is no cineplex you selected! Please try again!");
                    continue;
                }
            } else if(selection.equals("R")){
                return null;
            }else{
                System.out.println("Input cannot be recognised! Please try again!");
            }
        }
    }

    public MovieInCinema selectMovieInCinema() throws IOException {
        while(true) {
            if(this.selectedCinema!=null) {
                int counter = 1;
                for (MovieInCinema m : this.selectedCinema.getMoviesList()) {
                    System.out.println(counter + "." + m.getInCinemaMovie().getMovieName() + "\t" +
                            m.getInCinemaMovie().getMovieType() + "\t" + m.getInCinemaMovie().getMovieStatus());
                    counter++;
                }
                System.out.println("Please select the movie you want to see :-) Or input R to return previous page.");
                String selection = scanner.nextLine();
                if (Main.tryParseInteger(selection)) {
                    int temp = Integer.parseInt(selection) - 1;
                    if (temp < selectedCinema.getMoviesList().size()) {
                        this.selectedMovieInCinema = selectedCinema.getMoviesList().get(temp);
                        return this.selectedMovieInCinema;
                    }
                    System.out.println("Wrong number input, no such movie!");
                    continue;
                } else if (selection.equals("R")) {
                    return null;
                } else {
                    System.out.println("Input cannot be recognised! Please try again!");
                }
            }else{
                //TO-DO Print all movie and trace back where cinema will play it
                int cCounter = 1, ciCounter=1, micCounter=1;
                for(Cineplex c: cineplexesList){
                    System.out.println(cCounter+".Cineplex: "+c.getCineplexName()+"---------");
                    for(Cinema ci: c.getCinemaList()){
                        System.out.println("\t"+ciCounter+".Cinema: "+ci.getCinemaName()+" is showing");
                        for(MovieInCinema mic:ci.getMoviesList()){
                            System.out.println("\t\t"+micCounter+"."+mic.getInCinemaMovie().getMovieName());
                            micCounter++;
                        }
                        micCounter=1;
                        ciCounter++;
                    }
                    ciCounter=1;
                    cCounter++;
                }
                System.out.println("Please use format x,y,z to select Cineplex, Cinema and Movie respectively.Input R to return previous page");
                Scanner localScanner = new Scanner(System.in);
                String temp = localScanner.nextLine();
                if (temp.equals("R"))
                    return null;
                String[] selection = temp.split(",");
                while(true){
                    if(selection.length!=3 || !Main.tryParseInteger(selection[0]) || !Main.tryParseInteger(selection[1]) || !Main.tryParseInteger(selection[2])) {
                        System.out.println("Not recognised! Please select again!");
                        temp = scanner.nextLine();
                        selection = temp.split(",");
                    }else{
                        int tempInt = Integer.parseInt(selection[0]) - 1;
                        this.selectedCineplex = cineplexesList.get(tempInt);
                        tempInt=Integer.parseInt(selection[1])-1;
                        this.selectedCinema = this.selectedCineplex.getCinemaList().get(tempInt);
                        tempInt=Integer.parseInt(selection[2])-1;
                        this.selectedMovieInCinema = this.selectedCinema.getMoviesList().get(tempInt);
                        return this.selectedMovieInCinema;
                    }
                }
            }
        }
    }

    public MovieOnScene selectMovieOnScene() throws IOException {
        while(true){
            if(this.selectedMovieInCinema!=null){
                int counter = 1;
                CinemaDate tempCinemaDate=null;
                for(CinemaDate c: this.selectedMovieInCinema.getInCinemaShowTimes()){
                    System.out.println(counter+" "+c);
                    counter++;
                }
                System.out.println("Choose the show time!");
                String selection = scanner.nextLine();
                if (Main.tryParseInteger(selection)) {
                    int temp = Integer.parseInt(selection) - 1;
                    if (temp < this.selectedMovieInCinema.getInCinemaShowTimes().size()) {
                        tempCinemaDate = this.selectedMovieInCinema.getInCinemaShowTimes().get(temp);
                        for(MovieOnScene m:Main.getOnScenesMovieList()){
                            if(m.getOnSceneShowTime().isSameTime(tempCinemaDate) && m.getOnSceneMovie().equals(this.selectedMovieInCinema.getInCinemaMovie())){
                                this.selectedMovieOnScene=m;
                                return m;
                            }
                        }
                        MovieOnScene newMovieOnScene = new MovieOnScene(this.selectedMovieInCinema.getInCinemaMovie(),
                                this.selectedCinema,this.selectedCineplex,tempCinemaDate);
                        Main.getOnScenesMovieList().add(newMovieOnScene);
                        this.selectedMovieOnScene=newMovieOnScene;
                        return newMovieOnScene;
                    }
                    else {
                        System.out.println("Wrong number input, no such movie!");
                        continue;
                    }
                } else if (selection.equals("R")) {
                    return null;
                } else {
                    System.out.println("Input cannot be recognised! Please try again!");
                }
            }
        }
    }

    public void printMovieInfo() {
        System.out.println("Movie Name: "+ selectedMovieInCinema.getInCinemaMovie().getMovieName());
        System.out.println("Movie Director: "+ selectedMovieInCinema.getInCinemaMovie().getMovieDirector());
        System.out.println("Movie Type: "+ selectedMovieInCinema.getInCinemaMovie().getMovieType());
        System.out.println("Movie Status: "+ selectedMovieInCinema.getInCinemaMovie().getMovieStatus());
        System.out.println("Movie Synopsis: "+ selectedMovieInCinema.getInCinemaMovie().getMovieSynopsis());
        System.out.println("Movie Cast: ");
        for(String s: selectedMovieInCinema.getInCinemaMovie().getMovieCastList()){
            System.out.println("\t"+s);
        }

    }

    public void printBookingHistory()
    {
        Iterator<Booking> it = this.bookingsList.iterator();

        while(it.hasNext())
        {
            Booking temp = it.next();
            if(temp.getBookedMovieGoerName().contains(movieGoer.getUserName()))
            {
                String output = "Booked Transacation ID: "+temp.getBookedTransactionID()+"\nBooked Movie: "+temp.getBookedMovieOnScene().getOnSceneMovie().getMovieName()+
                        "\nBooked MovieGoer name: "+temp.getBookedMovieGoerName()+"\nBooked Sell Date: "+temp.getBookedSellDate().getCinemaDate()+"\nBooked Price: "+
                        temp.getBookedPrice()+"\nTicket info: \n\tAdult: "+temp.getTicketinfo()[0]+"\n\tChildern: "+temp.getTicketinfo()[1]+"\n\tElderly: "+temp.getTicketinfo()[2];
                System.out.println(output);
            }
        }
    }


    //Getters & setters
    public static IUser getMovieGoer() {
        return movieGoer;
    }

    public int[] getTicketing() {
        return ticketing;
    }

    public MovieOnScene getSelectedMovieOnScene() {
        return selectedMovieOnScene;
    }

    public Cinema getSelectedCinema() {
        return selectedCinema;
    }

    public MovieInCinema getSelectedMovieInCinema() {
        return selectedMovieInCinema;
    }

    public void setTicketing(int[] ticketing){
        this.ticketing =ticketing;
    }


    public boolean bookSeat(String selection) {
        String[] temp = selection.split(",");
        if(temp.length==2 && Main.tryParseInteger(temp[0])&&Main.tryParseInteger(temp[1]) && !tempBookedSeat.contains(selection)) {
            tempBookedSeat.add(selection);
            return true;
        }
        return false;
    }

    public boolean confirmeBook(){
        for(String s: tempBookedSeat)
            this.selectedMovieOnScene.addBookedSeat(s);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        String sellTime =sdf.format(cal.getTime());
        String transacationID = this.selectedCinema.getCinemaCode()+sellTime;

       Booking temp = new Booking(transacationID,this.selectedMovieOnScene,movieGoer.getUserName(),CinemaDate.parseCinemaDate(sellTime),finalPrice,ticketing,this.tempBookedSeat);
       Main.getBookingsList().add(temp);
       if(temp!=null){
           this.tempBookedSeat = new ArrayList<>();
           this.selectedMovieOnScene = null;
           this.selectedMovieInCinema=null;
           this.selectedCinema = null;
           this.selectedCineplex = null;
           this.ticketing=null;
            return true;
        }else {
           this.tempBookedSeat = new ArrayList<>();
           this.selectedMovieOnScene = null;
           this.selectedMovieInCinema=null;
           this.selectedCinema = null;
           this.selectedCineplex = null;
           this.ticketing=null;
            return false;
        }
    }

    public boolean printBookingDetails() throws IOException {
        while(true) {
            try {
                finalPrice = TicketPrice.getFinalPrice();
                System.out.println("Cineplex: " + this.selectedCineplex.getCineplexName());
                System.out.println("Cinema: " + this.selectedCinema.getCinemaName());
                System.out.println("Movie: " + this.selectedMovieOnScene.getOnSceneMovie().getMovieName());
                System.out.println("Show time: " + this.selectedMovieOnScene.getOnSceneShowTime().getCinemaDate());
                System.out.println("Ticket info: \n\tAdult:" + ticketing[0] + "Child:" + ticketing[1] + "Elderly:" + ticketing[2]);
                String seat = "";
                for (int i = 0; i < tempBookedSeat.size(); i++) {
                    if (i == tempBookedSeat.size())
                        seat += tempBookedSeat.get(i);
                    else
                        seat += tempBookedSeat.get(i) + ";";
                }
                System.out.println("Seat: " + seat);
                System.out.println("Price: " + finalPrice);
                System.out.println("Please check the information above and input Y to confirm the booking\n" +
                        "Any changes on booking? \n\tInput T for time-change, S for seat-change, M for movie-change");
                String select =scanner.nextLine();
                switch (select) {
                    case "Y":
                        confirmeBook();
                        return true;
                    case "S":
                        this.tempBookedSeat = new ArrayList<>();
                        movieGoerMgr.getSelectedMovieOnScene().printSeat();
                        for (int i = 1; i <= (ticketing[0] + ticketing[1] + ticketing[2]); i++) {
                            System.out.println("Please select the No." + i + " Seat");
                            String selection = scanner.nextLine();
                            if (!movieGoerMgr.bookSeat(selection)) {
                                System.out.println("Not recognised! Please select again!");
                                i--;
                            }
                        }
                        break;
                    case "T":
                        movieGoerMgr.selectMovieOnScene();
                        break;
                    case "M":
                        System.out.println("You will go back to home page for new booking");
                        this.tempBookedSeat = new ArrayList<>();
                        this.selectedMovieOnScene = null;
                        this.selectedMovieInCinema=null;
                        this.selectedCinema = null;
                        this.selectedCineplex = null;
                        this.ticketing=null;
                        return false;
                }
            }catch (Exception e){
                System.out.println("Wrong input detected, please check and try again!");

            }
        }

    }

    public boolean searchMovieByName() {
        int cCounter = 1, ciCounter = 1, micCounter = 1;
        System.out.println("Please enter a name of movie");
        String movieName = scanner.nextLine();
        boolean hasResult=false;
        for (Cineplex c : cineplexesList) {
            for (Cinema ci : c.getCinemaList()) {
                for (MovieInCinema m : ci.getMoviesList()) {
                    if (m.getInCinemaMovie().getMovieName().equals(movieName)) {
                        hasResult=true;
                        System.out.println(cCounter + ".Cineplex: " + c.getCineplexName() + "---------");
                        System.out.println("\t" + ciCounter + ".Cinema: " + ci.getCinemaName() + " is showing");
                        System.out.println("\t\t" + micCounter + "." + m.getInCinemaMovie().getMovieName());
                    }
                    micCounter++;
                }
                micCounter = 1;
                ciCounter++;
            }
            ciCounter = 1;
            cCounter++;
        }
        if(hasResult){
            System.out.println("Please use format x,y,z to select Cineplex, Cinema and Movie respectively.Input R to return previous page");
            Scanner localScanner = new Scanner(System.in);
            String temp = localScanner.nextLine();
            if (temp.equals("R"))
                return false;
            String[] selection = temp.split(",");
            while(true){
                if(selection.length!=3 || !Main.tryParseInteger(selection[0]) || !Main.tryParseInteger(selection[1]) || !Main.tryParseInteger(selection[2])) {
                    System.out.println("Not recognised! Please select again!");
                    temp = scanner.nextLine();
                    selection = temp.split(",");
                }else{
                    int tempInt = Integer.parseInt(selection[0]) - 1;
                    this.selectedCineplex = cineplexesList.get(tempInt);
                    tempInt=Integer.parseInt(selection[1])-1;
                    this.selectedCinema = this.selectedCineplex.getCinemaList().get(tempInt);
                    tempInt=Integer.parseInt(selection[2])-1;
                    this.selectedMovieInCinema = this.selectedCinema.getMoviesList().get(tempInt);
                    return true;
                }
            }
        }else {
            System.out.println("Sorry, not find the movie!");
            return false;
        }
    }
}
