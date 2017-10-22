package obj;

public class Review {
    private double rate;
    private String movieGoerName;
    private String movieName;
    private String comment;

    public Review() {
        rate=0.0d;
        movieGoerName=null;
        movieName=null;
        comment="";
    }

    public Review(double rate, String movieGoerName, String movieName, String comment) {
        this.rate = rate;
        this.movieGoerName = movieGoerName;
        this.movieName = movieName;
        this.comment = comment;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getMovieGoerName() {
        return movieGoerName;
    }

    public void setMovieGoerName(String movieGoerName) {
        this.movieGoerName = movieGoerName;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
