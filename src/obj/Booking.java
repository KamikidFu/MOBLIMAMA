package obj;

public class Booking {
    private String bookedTranscationID;
    private String bookedMovieName;
    private String bookedCinemaName;
    private String bookedMovieGoerName;
    private CinemaDate bookedSellDate;
    private double bookedPrice;

    public Booking() {
        bookedCinemaName="";
        bookedMovieName="";
        bookedMovieGoerName="";
        bookedPrice=0.0d;
        bookedSellDate=null;
        bookedTranscationID="";
    }

    public Booking(String bookedTranscationID, String bookedMovieName, String bookedCinemaName, String bookedMovieGoerName, CinemaDate bookedSellDate, double bookedPrice) {
        this.bookedTranscationID = bookedTranscationID;
        this.bookedMovieName = bookedMovieName;
        this.bookedCinemaName = bookedCinemaName;
        this.bookedMovieGoerName = bookedMovieGoerName;
        this.bookedSellDate = bookedSellDate;
        this.bookedPrice = bookedPrice;
    }

    public String getBookedTranscationID() {
        return bookedTranscationID;
    }

    public void setBookedTranscationID(String bookedTranscationID) {
        this.bookedTranscationID = bookedTranscationID;
    }

    public String getBookedMovieName() {
        return bookedMovieName;
    }

    public void setBookedMovieName(String bookedMovieName) {
        this.bookedMovieName = bookedMovieName;
    }

    public String getBookedCinemaName() {
        return bookedCinemaName;
    }

    public void setBookedCinemaName(String bookedCinemaName) {
        this.bookedCinemaName = bookedCinemaName;
    }

    public String getBookedMovieGoerName() {
        return bookedMovieGoerName;
    }

    public void setBookedMovieGoerName(String bookedMovieGoerName) {
        this.bookedMovieGoerName = bookedMovieGoerName;
    }

    public CinemaDate getBookedSellDate() {
        return bookedSellDate;
    }

    public void setBookedSellDate(CinemaDate bookedSellDate) {
        this.bookedSellDate = bookedSellDate;
    }

    public double getBookedPrice() {
        return bookedPrice;
    }

    public void setBookedPrice(double bookedPrice) {
        this.bookedPrice = bookedPrice;
    }
}
