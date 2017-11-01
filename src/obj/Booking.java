package obj;


import java.text.NumberFormat;

public class Booking implements IBookingBO, IBookingBiz{
    private String bookedTransactionID;
    private String bookedMovieName;
    private String bookedCinemaName;
    private String bookedCineplexName;
    private String bookedMovieGoerName;
    private CinemaDate bookedSellDate;
    private double bookedPrice;
    private int[] ticketinfo;

    public Booking() {
        bookedCinemaName="";
        bookedCineplexName="";
        bookedMovieName="";
        bookedMovieGoerName="";
        bookedPrice=0.0d;
        bookedSellDate=null;
        bookedTransactionID="";
        ticketinfo = new int[3];
    }

    public Booking(String bookedTranscationID, String bookedMovieName,
                   String bookedCinemaName, String bookedCineplexName,
                   String bookedMovieGoerName, CinemaDate bookedSellDate,
                   double bookedPrice, int[] ticketinfo) {
        this.bookedTransactionID = bookedTranscationID;
        this.bookedMovieName = bookedMovieName;
        this.bookedCinemaName = bookedCinemaName;
        this.bookedCineplexName = bookedCineplexName;
        this.bookedMovieGoerName = bookedMovieGoerName;
        this.bookedSellDate = bookedSellDate;
        this.bookedPrice = bookedPrice;
        this.ticketinfo = ticketinfo;
    }

    @Override
    public void viewBooking(IUser user){
        System.out.println("Hello "+user.getUserName()+"! The following is your booking details:");
        System.out.println("Transaction ID: "+this.bookedTransactionID);
        System.out.println("Movie Name: "+this.bookedMovieName);
        System.out.println("Cinema Name: "+ this.bookedCinemaName);
        System.out.println("Cineplex Name: "+this.bookedCineplexName);
        System.out.println("Movie Goer Name: "+this.bookedMovieGoerName);
        System.out.println("Sell Date: "+this.bookedSellDate.getSellDate());
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
    public String getBookedMovieName() {
        return bookedMovieName;
    }

    @Override
    public String getBookedCinemaName() {
        return bookedCinemaName;
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
    public String getBookedCineplexName() {
        return bookedCineplexName;
    }
}
