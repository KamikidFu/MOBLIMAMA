package module.moviegoer.views;


import java.util.Scanner;

public class View2
{
    static Scanner s1 = new Scanner(System.in);

    public static void runView2()
    {
        int i;

        System.out.println("Please Select: \n(1)Select Cineplex\n(2)View Movies\n(3)Show Booking History\n(4)List Top 5 Movies\n(5)Logout\n");

        i = s1.nextInt();

        switch(i)
        {
            case 1:
                View3.runView3();
                break;
            case 2:
                //List All Movies
                View3.runView3();
                break;
            case 3:
                //Show Booking History
                runView2();
                break;
            case 4:
                //List Top 5 Movies
                runView2();
                break;
        }

    }
}