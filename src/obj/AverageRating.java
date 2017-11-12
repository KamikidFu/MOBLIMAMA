package obj;

import java.util.ArrayList;

public class AverageRating extends Review implements Comparable <AverageRating> {
	double rate = super.getRate();
	String movie = super.getMovieName();
	private ArrayList<Review> reviewList;
	private ArrayList<Movie> moviesList;
	
	// for (int a = 0; a < moviesList.size)
	// https://stackoverflow.com/questions/23110853/java8-sum-values-from-specific-field-of-the-objects-in-a-list
	
	/*public AverageRating() {
		double aveRate = 0;
		for (int i = 0; i < reviewList.size(); i++)
			if (movie == Cinema.getMoviesList(i)[i])
			aveRate += Review.getRate();
		
		movie = null;
	}*/
	
	public int compareTo(AverageRating o) {
		if (this.rate < o.rate)
			return -1;
		else if (this.rate > o.rate)
			return 1;
		else {
			if (this.movie.compareTo(o.movie) < 0)
				return 1;
			return -1;
		}
	}
	
	
	public static void insertionSort (Comparable[] list)
	{
		for (int index = 1; index < list.length; index++)
		{
			Comparable key = list[index];
			int position = index;
			// Shift larger values to the right
			while (position > 0 && key.compareTo(list[position-1]) > 0)
			{
				list[position] = list[position-1];
				position--;
			}
				list[position] = key;
		}
	}
}
