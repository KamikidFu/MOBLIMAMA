package module.moviegoer.views;



import java.util.Scanner;

public class BookingConfirmationView
{
    static Scanner s5 = new Scanner(System.in);

    public static void runView8(boolean iterateSummary)
    {
        int input;

        if(iterateSummary)
        {
            //Print the Booking Summary
        }

        System.out.println("Please Confirm Your Decisions...\n(1)Confirm\n(2)Reselect Cineplex\n(1)Reselect Seats");

        input = s5.nextInt();

        switch(input)
        {
            case 1:
                //Confirm The Details Stated In Booking Summary & Leave View 8
                break;
            case 2 :
                //Reselect Cineplex
                CineplexSelectionView.run();
                break;
            case 3:
                //Reselect Seats
                SeatSelectionView.runView7();
                break;
            default:
                //Invalid Selection
                runView8(false);
        }

    }
}

