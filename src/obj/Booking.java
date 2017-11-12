package obj;


import app.Main;

import java.text.NumberFormat;
import java.util.ArrayList;

public class Booking implements IBookingBO, IBookingBiz{
    private String bookedTransactionID;
    private MovieOnScene bookedMovieOnScene;
    private String bookedMovieGoerName;
    private CinemaDate bookedSellDate;
    private double bookedPrice;
    private int[] ticketinfo;
    private ArrayList<String> bookedSeat;

    private static ArrayList<Booking> bookings = Main.getBookingsList();

    public Booking() {
        bookedMovieGoerName="";
        bookedPrice=0.0d;
        bookedSellDate=null;
        bookedTransactionID="";
        bookedMovieOnScene=null;
        ticketinfo = new int[3];
        bookedSeat=new ArrayList<>();
    }

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

    @Override
    public String getBookedTransactionID() {
        return bookedTransactionID;
    }


    @Override
    public String getBookedMovieGoerName() {
        return bookedMovieGoerName;
    }

    @Override
    public CinemaDate getBookedSellDate() {
        return bookedSellDate;
    }

    @Override
    public double getBookedPrice() {
        return bookedPrice;
    }

    @Override
    public int[] getTicketinfo() {
        return ticketinfo;
    }

    @Override
    public MovieOnScene getBookedMovieOnScene() {
        return bookedMovieOnScene;
    }

    @Override
    public ArrayList<String> getBookedSeat() {
        return bookedSeat;
    }

    public static void printTop5BySelling() {
        ArrayList<String> distinctMovie = new ArrayList<>();
        for(Booking b: bookings){
            if(!distinctMovie.contains(b.bookedMovieOnScene.getOnSceneMovie().getMovieName())){
                distinctMovie.add(b.bookedMovieOnScene.getOnSceneMovie().getMovieName());
            }
        }
        int[] rank = new int[distinctMovie.size()];
        for(int i=0;i<rank.length;i++){
            for(Booking b:bookings) {
                for (int j = 0; j < bookings.size(); j++) {
                    if(b.bookedMovieOnScene.equals(bookings.get(j).bookedMovieOnScene)){
                        rank[i]++;
                    }
                }
            }
        }
        int temp;
        int pointer=0, topCounter = 0;
        String output = "";
        for(int i=0;i<rank.length;i++) {
            temp = rank[i];
            topCounter++;
            if(topCounter==6) break;
            for (int j = 0; j < rank.length; j++) {
                if (temp <= rank[j]) {
                    temp = rank[j];
                    pointer = j;
                }
            }
            output+="No."+topCounter+" "+distinctMovie.get(pointer)+" Sell amount: "+rank[pointer]+"\n";
            rank[pointer]=0;
            pointer=0;
        }
        System.out.println(output);
    }
}
