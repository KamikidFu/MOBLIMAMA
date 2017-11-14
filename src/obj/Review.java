package obj;

import app.Main;

import java.util.ArrayList;

/**
 *Review entity class
 * @author Fu, Yunhao
 */
public class Review {
	private static ArrayList<Review> reviews =Main.getReviewsList();
	
    private double rate;
    private String movieGoerName;
    private String movieName;
    private String comment;

    /**
     * Default constructor
     */
    public Review() {
        rate=0.0d;
        movieGoerName=null;
        movieName=null;
        comment="";
    }

    /**
     * Full constructor
     * @param rate rate
     * @param movieGoerName movie goer name
     * @param movieName movie name
     * @param comment comment
     */
    public Review(double rate, String movieGoerName, String movieName, String comment) {
        this.rate = rate;
        this.movieGoerName = movieGoerName;
        this.movieName = movieName;
        this.comment = comment;
    }

    public static void printTop5ByRating(){
        ArrayList<String> distinctMovie = new ArrayList<>();
        for(Review r:reviews){
            if(!distinctMovie.contains(r.movieName)){
                distinctMovie.add(r.movieName);
            }
        }

        double[] rating = new double[distinctMovie.size()];

        for(int i=0; i<rating.length;i++){
            int reviewCounter = 0;
            for(Review r: reviews){
                if(r.movieName.equals(distinctMovie.get(i))){
                    reviewCounter++;
                    rating[i]+=r.rate;
                }
            }
            rating[i] = rating[i]/reviewCounter;
        }

        double temp = 0.0d;
        int pointer=0, topCounter = 0;
        String output = "";
        for(int i=0;i<rating.length;i++) {
            topCounter++;
            if(topCounter==6) break;
            for (int j = 0; j < rating.length; j++) {
                if (temp <= rating[j]) {
                    temp = rating[j];
                    pointer = j;
                }
            }
            output+="No."+topCounter+" "+distinctMovie.get(pointer)+" Average rate: "+rating[pointer]+"\n";
            rating[pointer]=0.0d;
            pointer=0;
            temp=0;
        }
        System.out.println(output);
    }

    /**
     * return rate
     * @return rate
     */
    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
    
    /**
     * return movie goer name
     * @return movie goer name
     */
    public String getMovieGoerName() {
        return movieGoerName;
    }

    /**
     * return movie name
     * @return name
     */
    public String getMovieName() {
        return movieName;
    }

    /**
     * return comment
     * @return comment
     */
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
