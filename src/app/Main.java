package app;

import exception.IllegalDataException;
import module.admin.AdminModule;
import module.admin.controllers.StaffMgr;
import module.login.LoginModule;
import module.moviegoer.MovieGoerModule;
import module.moviegoer.controllers.MovieGoerMgr;
import obj.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Main method is to run whole project
 * @author Fu, Yunhao
 * @version 1.3
 */
public class Main {
    /**
     * Static array lists for each related module use
     */

    private static ArrayList<Cinema> cinemasList = new ArrayList<>();
    private static ArrayList<Cineplex> cineplexesList = new ArrayList<>();
    private static ArrayList<Review> reviewsList = new ArrayList<>();
    private static ArrayList<Booking> bookingsList = new ArrayList<>();
    private static ArrayList<Movie> moviesList = new ArrayList<>();
    private static ArrayList<IUser> usersList = new ArrayList<>();
    private static Scanner systemScanner = new Scanner(System.in);
    /**
     * Application main entry point
     * @param args Pass data file into application
     * @throws IOException Throw any IO exceptions
     * @throws RuntimeException Throw any Run time exceptions
     */
    public static void main(String[] args)throws IOException, RuntimeException, ParseException{

        //dataLoaded is a boolean flag to check if the data file successfully loaded into system
        //exitFlag is a boolean flag to determine if the user wants to exit from the system
        boolean dataLoaded =readFile(new File("src/res/Data.txt"));
        boolean exitFlag = false;

        while(dataLoaded && !exitFlag){

            System.out.println("\nWelcome to MOBLIMA System!");

            //LoginModule loginModule = new LoginModule();
            IUser temp =(new LoginModule()).run();

            if(temp == null){
                System.out.println("Error: Wrong IUser Information!\n");
                continue;
            }else if(temp instanceof MovieGoer){
                ((MovieGoer) temp).doMovieGoerJob();
                exitFlag = true;
            }else if (temp instanceof Staff){
                ((Staff) temp).doAdminJob();
                exitFlag = true;
            }

            if(exitFlag)
                System.out.println("Thank you for using our system!");
        }

        //Close all scanner which are using System.in
        systemScanner.close();

        //UpdateFile()
        //updateFile(new File("src/res/Data.txt"));
        return;
    }

    private static boolean readFile(File file)throws IOException{
        Scanner sc = new Scanner(file);
        String oneLineData="";
        String[] oneLineDataArray;
        ArrayList<String> userNameList = new ArrayList<>();
        ArrayList<String> passWordList = new ArrayList<>();
        ArrayList<String> userTypeList = new ArrayList<>();
        try {
            while (sc.hasNext()) {
                oneLineData = sc.nextLine();
                if (oneLineData.contains("<USER>")) {
                    oneLineData = sc.nextLine();
                    oneLineDataArray = oneLineData.split(";");
                    while (!oneLineData.contains("</USER>")) {
                        userNameList.add(oneLineDataArray[0]);
                        userTypeList.add(oneLineDataArray[1]);
                        oneLineData = sc.nextLine();
                        oneLineDataArray = oneLineData.split(";");
                    }
                } else if (oneLineData.contains("<PASSWORD>")) {
                    oneLineData = sc.nextLine();
                    while (!oneLineData.contains("</PASSWORD>")) {
                        passWordList.add(oneLineData);
                        oneLineData = sc.nextLine();
                    }
                    if (userNameList.size() != passWordList.size()) {
                        throw new IllegalDataException(
                                "Wrong user data retrieved from file.");
                    }else{
                        for(int i=0;i<userNameList.size();i++){
                            if(userTypeList.get(i).equals("Staff"))
                                usersList.add(new Staff(userNameList.get(i), passWordList.get(i)));
                            else
                                usersList.add(new MovieGoer(userNameList.get(i),passWordList.get(i)));
                        }
                    }
                } else if (oneLineData.contains("<MOVIE>")) {
                    oneLineData = sc.nextLine();
                    while(!oneLineData.contains("</MOVIE>")){
                        oneLineDataArray = oneLineData.split(";");
                        if(oneLineDataArray.length >= 7) {
                            moviesList.add(new Movie(
                                    oneLineDataArray[0], oneLineDataArray[1],
                                    oneLineDataArray[2], oneLineDataArray[3],
                                    oneLineDataArray[4],
                                    Arrays.copyOfRange(
                                            oneLineDataArray,
                                            5, oneLineDataArray.length)));
                        }else{
                            throw new IllegalDataException(
                                    "Wrong movie data retrieved from file");
                        }
                        oneLineData = sc.nextLine();
                    }
                }else if(oneLineData.contains("<CINEMA>")){
                    oneLineData=sc.nextLine();
                    while(!oneLineData.contains("</CINEMA>")){
                        oneLineDataArray = oneLineData.split(";");
                        if(oneLineDataArray.length>=3){
                            if(oneLineDataArray.length==3){
                                cinemasList.add(new Cinema(
                                        oneLineDataArray[0],oneLineDataArray[1],oneLineDataArray[2]));
                            }else{
                                ArrayList<Movie> tempMovieList = new ArrayList<>();
                                for(int i=3; i<oneLineDataArray.length;i++){
                                    for(int j=0; j<moviesList.size();j++){
                                        Movie tempMovie = moviesList.get(j);
                                        if(tempMovie.getMovieName().equals(oneLineDataArray[i])){
                                            tempMovieList.add(tempMovie);
                                            break;
                                        }
                                    }
                                }
                                cinemasList.add(new Cinema(
                                        oneLineDataArray[0],oneLineDataArray[1],
                                        oneLineDataArray[2],tempMovieList));
                            }
                        }else{
                            throw new IllegalDataException(
                                    "Wrong cinema data retrieved from file"
                            );
                        }
                        oneLineData=sc.nextLine();
                    }
                }else if(oneLineData.contains("<CINEPLEX>")){
                    oneLineData = sc.nextLine();
                    while(!oneLineData.contains("</CINEPLEX>")){
                        oneLineDataArray=oneLineData.split(";");
                        if(oneLineDataArray.length>=2){
                            if(oneLineDataArray.length==2) {
                                cineplexesList.add(new Cineplex(
                                        oneLineDataArray[0], oneLineDataArray[2]
                                ));
                            }else {
                                ArrayList<Cinema> tempCinemaList = new ArrayList<>();
                                for (int i = 1; i < oneLineDataArray.length; i++) {
                                    for (int j = 0; j < cinemasList.size(); j++) {
                                        Cinema tempCinema = cinemasList.get(j);
                                        if (tempCinema.getCinemaName().equals(oneLineDataArray[i])) {
                                            tempCinemaList.add(tempCinema);
                                            break;
                                        }
                                    }
                                }
                                cineplexesList.add(new Cineplex(
                                        oneLineDataArray[0], oneLineDataArray[1],
                                        tempCinemaList
                                ));
                            }
                        }else{
                            throw new IllegalDataException(
                                    "Wrong cineplex data retrieved from file"
                            );
                        }
                        oneLineData=sc.nextLine();
                    }
                }else if(oneLineData.contains("<REVIEW")){
                    oneLineData = sc.nextLine();
                    while(!oneLineData.contains("</REVIEW>")){
                        oneLineDataArray = oneLineData.split(";");
                        if(oneLineDataArray.length==4){
                            reviewsList.add(new Review(
                                    Double.parseDouble(oneLineDataArray[0]),
                                    oneLineDataArray[1],
                                    oneLineDataArray[2],oneLineDataArray[3]));
                        }else{
                            throw new IllegalDataException(
                                    "Wrong review data retrieved from file"
                            );
                        }
                        oneLineData = sc.nextLine();
                    }
                }else if(oneLineData.contains("<BOOKING>")){
                    oneLineData = sc.nextLine();
                    while(!oneLineData.contains("</BOOKING>")){
                        oneLineDataArray=oneLineData.split(";");
                        if(oneLineDataArray.length==8){
                            int[] ticketTemp = new int[3];
                            for(int i=0;i<ticketTemp.length;i++){
                                ticketTemp[i] = Integer.parseInt(oneLineDataArray[7].split(",")[i]);
                            }
                            bookingsList.add(new Booking(
                               oneLineDataArray[0],
                               oneLineDataArray[1],
                               oneLineDataArray[2],
                               oneLineDataArray[3],
                               oneLineDataArray[4],
                               new CinemaDate(
                                       (Integer.parseInt(oneLineDataArray[5].substring(0,4))),
                                       (Integer.parseInt(oneLineDataArray[5].substring(4,6))),
                                       Integer.parseInt(oneLineDataArray[5].substring(6,8)),
                                       Integer.parseInt(oneLineDataArray[5].substring(8,10)),
                                       Integer.parseInt(oneLineDataArray[5].substring(10,12))),
                               Double.parseDouble(oneLineDataArray[6]),ticketTemp
                            ));
                        }else{
                            throw new IllegalDataException(
                                    "Wrong booking data retrieved from file"
                            );
                        }
                        oneLineData = sc.nextLine();
                    }
                }else{
                    throw new IllegalDataException(
                            "Wrong data retrieved from file"
                    );
                }
            }
        }catch (IllegalDataException e){
            System.err.print(e);
            return false;
        }catch (Exception e){
            System.err.println(e);
            return false;
        }
        return true;
    }

    private static void updateFile(File file) throws IOException{
        FileWriter fileWriter = new FileWriter(file,false);

        fileWriter.write("<USER>"+System.lineSeparator());
        for(IUser u:usersList){
            fileWriter.write(u.getUserName()+(u instanceof Staff?";Staff":";MovieGoer")+System.lineSeparator());
        }
        fileWriter.write("</USER>"+System.lineSeparator());

        fileWriter.write("<PASSWORD>"+System.lineSeparator());
        for(IUser u:usersList){
            fileWriter.write(u.getPassword()+System.lineSeparator());
        }
        fileWriter.write("</PASSWORD>"+System.lineSeparator());

        fileWriter.write("<MOVIE>"+System.lineSeparator());
        for(Movie m:moviesList){
            String[] cast = m.getMovieCast();
            String castString="";
            for(int i=0;i<cast.length;i++){
                castString+=";";
                castString+=cast[i];
            }
            fileWriter.write(m.getMovieName()+";"+m.getMovieType()+";"+m.getMovieStatus()+
                            ";"+m.getMovieSynopsis()+";"+m.getMovieDirector()+castString+System.lineSeparator());
        }
        fileWriter.write("</MOVIE>"+System.lineSeparator());

        fileWriter.write("<CINEMA>"+System.lineSeparator());
        for(Cinema c:cinemasList){
            ArrayList<Movie> movie = c.getMoviesList();
            String movieString="";
            for(Movie m:movie){
                movieString+=";";
                movieString+=m.getMovieName();
            }
            fileWriter.write(c.getCinemaName()+";"+c.getCinemaClass()+";"+c.getCinemaCode()+movieString+System.lineSeparator());
        }
        fileWriter.write("</CINEMA>"+System.lineSeparator());

        fileWriter.write("<CINEPLEX>"+System.lineSeparator());
        for(Cineplex c:cineplexesList){
            ArrayList<Cinema> cinemas = c.getCinemaList();
            String cinemaString="";
            for(Cinema ci:cinemas){
                cinemaString+=";";
                cinemaString+=ci.getCinemaName();
            }
            fileWriter.write(c.getCineplexName()+";"+c.getCineplexLocation()+cinemaString+System.lineSeparator());
        }
        fileWriter.write("</CINEPLEX>"+System.lineSeparator());

        fileWriter.write("<REVIEW>"+System.lineSeparator());
        for (Review r:reviewsList){
            fileWriter.write(r.getRate()+";"+r.getMovieGoerName()+";"+r.getMovieName()+";"+r.getComment()+System.lineSeparator());
        }
        fileWriter.write("</REVIEW>"+System.lineSeparator());

        fileWriter.write("<BOOKING>"+System.lineSeparator());
        for(Booking b:bookingsList){

            fileWriter.write(b.getBookedTransactionID()+";"+b.getBookedMovieName()+";"+b.getBookedCinemaName()+
                            ";"+b.getBookedMovieGoerName()+";"+b.getBookedSellDate().getSellDate()+";"+b.getBookedPrice()+System.lineSeparator());
        }
        fileWriter.write("</BOOKING>");

        fileWriter.close();
    }

    public static ArrayList<Cinema> getCinemasList() {
        return cinemasList;
    }

    public static ArrayList<Cineplex> getCineplexesList() {
        return cineplexesList;
    }

    public static ArrayList<Review> getReviewsList() {
        return reviewsList;
    }

    public static ArrayList<Booking> getBookingsList() {
        return bookingsList;
    }

    public static ArrayList<Movie> getMoviesList() {
        return moviesList;
    }

    public static ArrayList<IUser> getUsersList() {
        return usersList;
    }

    public static Scanner getSystemScanner() {
        return systemScanner;
    }
}
