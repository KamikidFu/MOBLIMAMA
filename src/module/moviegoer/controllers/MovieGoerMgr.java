package module.moviegoer.controllers;

import app.Main;
import obj.*;
import obj.interfaces.IUser;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Movie goer manager class is a control class for any processing related to a movie goer user
 * @author ANG SHU LIANG
 * @author Fu, Yunhao
 * @author LEONG MEI HAN
 * @author Jon Chew
 */
public class MovieGoerMgr {
    private static Scanner scanner = Main.getSystemScanner();
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

    /**
     * Mono constructor for only generate one object
     */
    private MovieGoerMgr(){}
    static {
        movieGoerMgr = new MovieGoerMgr();
    }

    /**
     * Return mono MovieGoerMgr
     * @return MovieGoerMgr object
     */
    public static MovieGoerMgr getMovieGoerMgr(){
        if(movieGoer==null) {
            System.err.println("Manager has no user value");
            return null;
        }
        return movieGoerMgr;
    }

    /**
     * Activate MovieGoer when there comes a user who is moviegoer
     * @param MovieGoer User who is moviegoer
     */
    public static void activateMovieGoerMgr(IUser MovieGoer){movieGoer = MovieGoer;}



    //Methods interfaces

    /**
     * To select a cineplex
     * @return Selected cineplex
     * @throws IOException
     */
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

    /**
     * To select a cinema
     * @return Selected cinema
     * @throws IOException
     */
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

    /**
     * To select a movie in cinema
     * @return MovieInCinema object
     * @throws IOException
     */
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

    /**
     * To select a movie on scene
     * @return Selected movie on scene
     * @throws IOException
     */
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

    /**
     * To print the selected movie info
     */
    public void printMovieInfo() {
        System.out.println("Movie Title: "+ selectedMovieInCinema.getInCinemaMovie().getMovieName());
        System.out.println("Movie Director: "+ selectedMovieInCinema.getInCinemaMovie().getMovieDirector());
        System.out.println("Movie Type: "+ selectedMovieInCinema.getInCinemaMovie().getMovieType());
        System.out.println("Movie Status: "+ selectedMovieInCinema.getInCinemaMovie().getMovieStatus());
        System.out.println("Movie Synopsis: "+ selectedMovieInCinema.getInCinemaMovie().getMovieSynopsis());
        System.out.println("Movie Cast: ");
        for(String s: selectedMovieInCinema.getInCinemaMovie().getMovieCastList()){
            System.out.println("\t"+s);
        }
        System.out.println("Review:");
        for(Review r:reviewsList){
            if(selectedMovieInCinema.getInCinemaMovie().getMovieName().contains(r.getMovieName())){
                System.out.println("Reviewer:"+r.getMovieGoerName()+"\tRate:"+r.getRate()+"\nComment:"+r.getComment()+System.lineSeparator());
            }
        }
    }

    /**
     * To print user's booking history
     */
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
                        temp.getBookedPrice()+"\nTicket info: \n\tStudent: "+temp.getTicketinfo()[0]+"\n\tSenior Citizen: "+temp.getTicketinfo()[1]+"\n\tStandard: "+temp.getTicketinfo()[2];
                System.out.println(output);
            }
        }
    }


    //Getters & setters

    /**
     * Return the moviegoer user
     * @return Currently signed in movie goer user
     */
    public static IUser getMovieGoer() {
        return movieGoer;
    }

    /**
     * Return ticketing information
     * @return Ticketing information
     */
    public int[] getTicketing() {
        return ticketing;
    }

    /**
     * Return the selected movie on scene
     * @return Selected movieOnScene object
     */
    public MovieOnScene getSelectedMovieOnScene() {
        return selectedMovieOnScene;
    }

    /**
     * Return the selected cinema
     * @return Selected cinema object
     */
    public Cinema getSelectedCinema() {
        return selectedCinema;
    }

    /**
     * Return the selected MovieInCinema object
     * @return MovieInCinema object
     */
    public MovieInCinema getSelectedMovieInCinema() {
        return selectedMovieInCinema;
    }

    /**
     * Set the ticketing information
     * @param ticketing Ticketing info
     */
    public void setTicketing(int[] ticketing){
        this.ticketing =ticketing;
    }

    /**
     * Book a seat
     * @param selection Seat selection
     * @return Boolean value whether the seat can be booked
     */
    public boolean bookSeat(String selection) {
        String[] temp = selection.split(",");
        if(temp.length==2 && Main.tryParseInteger(temp[0])&&Main.tryParseInteger(temp[1]) &&
                !tempBookedSeat.contains(selection) && !this.selectedMovieOnScene.getBookedSeat().contains(selection)) {
            tempBookedSeat.add(selection);
            return true;
        }
        return false;
    }

    /**
     * Confirm book
     * @return  Boolean value whether the book is confirmed by user
     */
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

    /**
     * Print the current booking details
     * @return Boolean value if the booking processed successfully
     * @throws IOException
     */
    public boolean printBookingDetails() throws IOException {
        while(true) {
            try {
                finalPrice = theTotalPrice(this.getSelectedMovieOnScene(), this.getTicketing());
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

    /**
     * Search movie by name
     * @return Boolean value whether there is a movie named as input
     */
    public boolean searchMovieByName() {
        int cCounter = 1, ciCounter = 1, micCounter = 1;
        System.out.println("Please enter a movie title");
        String movieName = scanner.nextLine();
        boolean hasResult=false;
        for (Cineplex c : cineplexesList) {
            for (Cinema ci : c.getCinemaList()) {
                for (MovieInCinema m : ci.getMoviesList()) {
                    if (m.getInCinemaMovie().getMovieName().contains(movieName)) {
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

    /**
     *  Calculate the price of booking
     * @param movieOnScene Movie the user wants to see
     * @param ticketInfo Tickets information
     * @return
     */
    public static double theTotalPrice(MovieOnScene movieOnScene, int[] ticketInfo) {
        double totalPrice = 0.0d;
        CinemaDate showTime = movieOnScene.getOnSceneShowTime();
        String day = showTime.convertDateToDay();
        String type = movieOnScene.getOnSceneMovie().getMovieType();
        System.out.println("The day is " + day);
        // check whether showTime is weekend or weekday
        if (day.equals("Sat")|| day.equals("Sun")|| CinemaDate.isHoliday(showTime)) {
            TicketPrice.setWeekday(false);
            System.out.println("I have set weekday false");
        }

        // check whether showType is digital or 3D
        if (type.equals("3D")) {
            TicketPrice.setDigital(false);
            System.out.println("I have set digital false");
        }

        // check whether student
        for (int i = 0; i < ticketInfo[0]; i++) {
            TicketPrice.setStudent(ticketInfo[0] > 0);
            TicketPrice.setSenior(false);
            TicketPrice.converter(TicketPrice.isStudent(), TicketPrice.isSenior(), TicketPrice.isWeekday(), TicketPrice.isDigital());
            totalPrice += TicketPrice.oneTicketPrice(TicketPrice.getDayMultiplier(), TicketPrice.getAgeBenefit(), TicketPrice.getMovieTypeExtra());
        }

        // check whether senior
        for (int i = 0; i < ticketInfo[1]; i++) {
            TicketPrice.setSenior(ticketInfo[1] > 0);
            TicketPrice.setStudent(false);
            TicketPrice.converter(TicketPrice.isStudent(), TicketPrice.isSenior(), TicketPrice.isWeekday(), TicketPrice.isDigital());
            totalPrice += TicketPrice.oneTicketPrice(TicketPrice.getDayMultiplier(), TicketPrice.getAgeBenefit(), TicketPrice.getMovieTypeExtra());
        }

        // check whether standard
        for(int i = 0; i < ticketInfo[2]; i++) {
            TicketPrice.setSenior(false);
            TicketPrice.setStudent(false);
            TicketPrice.converter(TicketPrice.isStudent(), TicketPrice.isSenior(), TicketPrice.isWeekday(), TicketPrice.isDigital());
            totalPrice += TicketPrice.oneTicketPrice(TicketPrice.getDayMultiplier(), TicketPrice.getAgeBenefit(), TicketPrice.getMovieTypeExtra());
        }

        //Final price
        return totalPrice;
    }
}
