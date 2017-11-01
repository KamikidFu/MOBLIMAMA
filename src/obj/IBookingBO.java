package obj;

/**
 * IBookingBO class is a class for controlling Business object of Booking
 * @author Fu, Yunhao
 */
public interface IBookingBO {
    //Getters
    String getBookedTransactionID();
    String getBookedMovieName();
    String getBookedCinemaName();
    String getBookedMovieGoerName();
    CinemaDate getBookedSellDate();
    double getBookedPrice();
    int[] getTicketinfo();
    String getBookedCineplexName();
}
