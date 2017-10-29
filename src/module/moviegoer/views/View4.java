package module.moviegoer.views;


import java.util.Scanner;

public class View4
{
    static Scanner s2 = new Scanner(System.in);
    private static int[] tickets = new int[3]; //adult,child,elderly

    public static int[] getTickets()
    {
        return tickets;
    }

    public void runView4(boolean listMovies)//The boolean tells us whether or not we should list out all the movies
    {
        int i;

        //List All Movies & Use Number Codes to order them
        System.out.println("(1)Select a Movie\n(2)Movie Info\n");

        i = s2.nextInt();

        switch(i)
        {
            case 1:
                System.out.println("How many Adult Tickets?\n");
                tickets[0] = s2.nextInt();
                System.out.println("How many Child Tickets?\n");
                tickets[1] = s2.nextInt();
                System.out.println("How many Elderly Tickets?\n");
                tickets[2] = s2.nextInt();
                break;
            case 2:
                //View5.runView5();
                break;
            default:

        }
    }

}