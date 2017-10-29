package module.moviegoer.views;

import java.util.Scanner;

public class View6
{
    static Scanner s3 = new Scanner(System.in);

    public void runView6()
    {
        int showtime;

        //Lists Showtimes
        System.out.println("Select a timing: \n");

        showtime = s3.nextInt();
        //Record the showtime info
        View7.runView7();
    }
}
