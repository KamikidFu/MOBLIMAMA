package module.admin.views;


import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import app.Main;
import obj.Cinema;
import obj.CinemaDate;
import obj.Movie;

public class MovieMethodView {

    private static Scanner scanner = Main.getSystemScanner();
    public static void newMovie() throws ParseException, IOException {
        List<Movie> mList = app.Main.getMoviesList();
        String mName,mType,mStatus,mSynopsis,mDirector,temp;
        String[] mCast = new String[20];
        Date[] mShowTime=new Date[10];
        int i=1;

        System.out.println("-------------------------------------");
        System.out.println("New Movie");
        System.out.println("-------------------------------------");
        System.out.println("Enter Movie Title: ");
        mName = scanner.nextLine();
        System.out.println("Enter Movie Type (Digital / 3D / Blockbuster) : ");
        mType = scanner.nextLine();
        System.out.println("Enter Movie Status (Coming Soon/ Preview / Now Showing / End of Showing) :");
        mStatus = scanner.nextLine();
        System.out.println("Enter Movie Synopsis: ");
        mSynopsis = scanner.nextLine();
        System.out.println("Enter Movie Director: ");
        mDirector = scanner.nextLine();
        do {
            System.out.println("Enter Movie Cast" + i + " Name (Press Enter if no more cast): ");
            temp = scanner.nextLine();
            if (!temp.isEmpty()) {
                mCast[i-1] = temp;
                i++;}
        } while (!temp.isEmpty());
        newShowTime(mShowTime);

        Movie movie = new Movie(mName,mType,mStatus,mSynopsis,mDirector,mCast);
        mList.add(movie);
        //saveMovie(mList);

        System.out.println("Your Movie has been added successfully.");
        System.out.println("Following are the Movie Details:");
        System.out.println("========================================");
        System.out.println("Movie Title: " + mName);
        System.out.println("Type: " + mType);
        System.out.println("Status: " + mStatus);
        System.out.println("Synopsis: " + mSynopsis);
        System.out.println("Director: " + mDirector);
        System.out.println("Casts : ");
        for (int n=0; n<i-1;n++)
            System.out.println(mCast[n]);
        System.out.println("ShowTime : ");
        for (int n=0; n<i-1;n++) {
            DateFormat outDF = new SimpleDateFormat( "EEE dd MMM yyyy hh:mm aa");
            String printDate = outDF.format(mShowTime[n]);
            System.out.println(printDate);
        }
    } //end of newMovie method


    public static void newShowTime(Date[] mShowTime) throws IOException, ParseException {
        String cineplex, cinema;
        Scanner sc = new Scanner(System.in);
        String inTime;
        int i=0;
//		do {
        System.out.println("-------------------------------------");
        System.out.println("New Movie Showtime");
        System.out.println("-------------------------------------");
        System.out.println("\nPress Enter to return Terminate current process");
        System.out.println("\nEnter Cineplex showing the movie: ");
        cineplex = sc.nextLine();
        System.out.println("Enter Cinema showing the movie: ");
        cinema = sc.nextLine();
/*
Bug: Parse the datetime error
        do {
            DateFormat inDF = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
            System.out.println("Enter Screening Date (dd/mm/yyyy): ");
            inTime = sc.nextLine();
            if (!inTime.isEmpty()) {
                Date showtime = inDF.parse(inTime);
                mShowTime[i] = showtime;
                i++;
            }

        } while (!inTime.isEmpty());
        */
        do{
            System.out.println("Enter Screening Date (hh/min/dd/mm/yyyy): ");
            inTime = sc.nextLine();
            if(!inTime.isEmpty()){
                String[] temp = inTime.split("/");
                CinemaDate inDF = new CinemaDate(Integer.parseInt(temp[4]),
                        Integer.parseInt(temp[3]),Integer.parseInt(temp[2]),
                        Integer.parseInt(temp[1]),Integer.parseInt(temp[0]));
                mShowTime[i] = inDF;
                i++;
            }

        }while(!inTime.isEmpty());
//		} while (!cineplex.isEmpty());
    } //end of newShowTime

/*
    public static void saveMovie(List mov) throws IOException {

        List mv = new ArrayList();

        for (int i = 0; i < mov.size(); i++) {
            Movie movie = (Movie) mov.get(i);
            StringBuilder st = new StringBuilder();
            st.append(movie.getMovieName().trim());
            st.append(System.lineSeparator());
            st.append(movie.getMovieType().trim());
            st.append(System.lineSeparator());
            st.append(movie.getMovieStatus().trim());
            st.append(System.lineSeparator());
            st.append(movie.getMovieSynopsis().trim());
            st.append(System.lineSeparator());
            st.append(movie.getMovieDirector().trim());
            st.append(System.lineSeparator());
            st.append(movie.getMovieCast());
            st.append(System.lineSeparator());
            mv.add(st.toString());
        }
        write(mv);
    } //end of saveMovie
*/
    /*
    public static void write(List data) throws IOException {
        String filename = "MovieDetails.txt";
        PrintWriter out = new PrintWriter(new FileWriter(filename));

        try {
            for (int i = 0; i < data.size(); i++) {
                out.println((String) data.get(i));
            }
        } finally {
            out.close();
        }
    }// end of write
*/

    /** Read the contents of the given file. */
    /*
    public List read(String fileName) throws IOException {
        List data = new ArrayList();
        Scanner scanner = new Scanner(new FileInputStream(fileName));
        try {
            while (scanner.hasNextLine()) {
                data.add(scanner.nextLine());
            }
        } finally {
            scanner.close();
        }
        return data;
    }// end of read
    */
}

