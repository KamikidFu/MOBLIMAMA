package obj;

import java.util.ArrayList;

public class Rating extends Review {
	
	double rate = super.getRate();
	String movie = super.getMovieName();
	private ArrayList<Review> reviewList;
}
