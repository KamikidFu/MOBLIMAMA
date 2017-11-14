package obj;


import app.Main;
import obj.interfaces.IBookingBO;
import obj.interfaces.IBookingBiz;
import obj.interfaces.IUser;

import java.text.NumberFormat;
import java.util.ArrayList;

/**
 *Booking entity class, implemented from IBookingBo and IBookingBiz
 * @author Fu, Yunhao
 * @version 1.3
 * @since 2017-10-18
 */
public class Booking implements IBookingBO, IBookingBiz {
    private String bookedTransactionID;
    private MovieOnScene bookedMovieOnScene;
    private String bookedMovieGoerName;
    private CinemaDate bookedSellDate;
    private double bookedPrice;
    private int[] ticketinfo;
    private ArrayList<String> bookedSeat;

    private static ArrayList<Booking> bookings = Main.getBookingsList();

    /**
     * Default constructor
     */
    public Booking() {
        bookedMovieGoerName="";
        bookedPrice=0.0d;
        bookedSellDate=null;
        bookedTransactionID="";
        bookedMovieOnScene=null;
        ticketinfo = new int[3];
        bookedSeat=new ArrayList<>();
    }

    /**
     * Full constructor
     * @param bookedTransactionID The transaction ID
     * @param bookedMovieOnScene The booked movie on scene object
     * @param bookedMovieGoerName The movie goer name
     * @param bookedSellDate The selling date of this booking
     * @param bookedPrice The price of this booking
     * @param ticketinfo The ticket information, containing the amount of different types of ticket
     * @param bookedSeat The booked seat for movie
     */
    public Booking(String bookedTransactionID, MovieOnScene bookedMovieOnScene,
                   String bookedMovieGoerName, CinemaDate bookedSellDate,
                   double bookedPrice, int[] ticketinfo,ArrayList<String> bookedSeat) {
        this.bookedTransactionID = bookedTransactionID;
        this.bookedMovieOnScene = bookedMovieOnScene;
        this.bookedMovieGoerName = bookedMovieGoerName;
        this.bookedSellDate = bookedSellDate;
        this.bookedPrice = bookedPrice;
        this.ticketinfo = ticketinfo;
        this.bookedSeat=bookedSeat;
    }

    /**
     * Override from IBookingBiz
     * viewBooking is to print all the information about this booking
     * @param user The IUser, most of time is the moviegoer user.
     */
    @Override
    public void viewBooking(IUser user){
        System.out.println("Hello "+user.getUserName()+"! The following is your booking details:");
        System.out.println("Transaction ID: "+this.bookedTransactionID);
        System.out.println("Movie Goer Name: "+this.bookedMovieGoerName);
        System.out.println("Sell Date: "+this.bookedSellDate.getCinemaDate());
        System.out.println("Price: "+NumberFormat.getCurrencyInstance().format(this.bookedPrice));
        System.out.println("Tickets info: ");
            for(int i=0;i<ticketinfo.length;i++){
                if(i==0 && ticketinfo[i]!=0)
                    System.out.println("\tAdult ticket(s): "+ticketinfo[i]);
                if(i==1 && ticketinfo[i]!=0)
                    System.out.println("\tChild ticket(s): "+ticketinfo[i]);
                if(i==2 && ticketinfo[i]!=0)
                    System.out.println("\tElderly ticket(s): "+ticketinfo[i]);
            }
    }

    /**
     * Override from IBookingBo
     * return the transaction ID
     * @return Transaction Id
     */
    @Override
    public String getBookedTransactionID() {
        return bookedTransactionID;
    }

    /**
     * Override from IBookingBo
     * return the movie goer name
     * @return Movie goer name
     */
    @Override
    public String getBookedMovieGoerName() {
        return bookedMovieGoerName;
    }

    /**
     * Override from IBookingBo
     * return the sell date
     * @return Sell date
     */
    @Override
    public CinemaDate getBookedSellDate() {
        return bookedSellDate;
    }

    /**
     * Override from IBookingBo
     * return the price of booking
     * @return Price
     */
    @Override
    public double getBookedPrice() {
        return bookedPrice;
    }

    /**
     * Override from IBookingBo
     * return the ticket information
     * @return Ticket information
     */
    @Override
    public int[] getTicketinfo() {
        return ticketinfo;
    }

    /**
     * Override from IBookingBo
     * return the movie on scene
     * @return Movie on scene
     */
    @Override
    public MovieOnScene getBookedMovieOnScene() {
        return bookedMovieOnScene;
    }

    /**
     * Override from IBookingBo
     * return the seat information
     * @return Seat information
     */
    @Override
    public ArrayList<String> getBookedSeat() {
        return bookedSeat;
    }


}
