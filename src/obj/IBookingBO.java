package obj;

import java.util.ArrayList;

/**
 * IBookingBO class is a class for controlling Business object of Booking
 * @author Fu, Yunhao
 */
public interface IBookingBO {
    //Getters
    String getBookedTransactionID();
    String getBookedMovieGoerName();
    CinemaDate getBookedSellDate();
    double getBookedPrice();
    int[] getTicketinfo();
    MovieOnScene getBookedMovieOnScene();
    ArrayList<String> getBookedSeat();
}
