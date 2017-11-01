package module.moviegoer.views;


import java.util.Scanner;

public class SeatSelectionView
{
    static Scanner s4 = new Scanner(System.in);
    //Store temp seating arrangement data here in case Moviegoer wishes to redo the selection form

    static int[] ticketinfo = new int[3];

    public static void runView7()
    {
        int row, column, tixnumber;
        //ticketinfo = MovieSelectionView.getTicketsType();
        tixnumber = ticketinfo[0] + ticketinfo[1] + ticketinfo[2];

        //Show Available Seats

        while(tixnumber>0)
        {
            System.out.println("Select a Row");
            row = s4.nextInt();
            System.out.println("Select a Column");
            column = s4.nextInt();

            //if(Seat Already Occupied){Prompt IUser & Get Him to Rechoose Options; continue;}
            //else{Record the information; tixnumber--;}

        }

        BookingConfirmationView.runView8(true);

    }
}
