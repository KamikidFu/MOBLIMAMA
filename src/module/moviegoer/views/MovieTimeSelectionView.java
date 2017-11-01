package module.moviegoer.views;

import java.util.Scanner;

public class MovieTimeSelectionView
{
    static Scanner s3 = new Scanner(System.in);

    public void runView6()
    {
        int showtime;

        //Lists Showtimes
        System.out.println("Select a timing: \n");

        showtime = s3.nextInt();
        //Record the showtime info
        SeatSelectionView.runView7();
    }
}
