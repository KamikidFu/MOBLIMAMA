package app;

import exception.IllegalDataException;
import module.login.LoginModule;
import obj.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

/**
 * Main method is to run whole project
 * @author Fu, Yunhao
 * @version 1.2
 */
public class Main {
    /**
     * Static array lists for each related module use
     */
    private static ArrayList<User> usersList = new ArrayList<>();
    private static ArrayList<Movie> moviesList = new ArrayList<>();
    private static ArrayList<Cinema> cinemasList = new ArrayList<>();
    private static ArrayList<Cineplex> cineplexesList = new ArrayList<>();
    private static ArrayList<Review> reviewsList = new ArrayList<>();
    private static ArrayList<Booking> bookingsList = new ArrayList<>();

    /**
     * Application main entry point
     * @param args Pass data file into application
     * @throws IOException Throw any IO exceptions
     * @throws RuntimeException Throw any Run time exceptions
     */
    public static void main(String[] args)throws IOException, RuntimeException{
        //Check the args length, it must contain one argument
        if (args.length != 1)
            return;

        //dataLoaded is a boolean flag to check if the data file successfully loaded into system
        //exitFlag is a boolean flag to determine if the user wants to exit from the system
        boolean dataLoaded =readFile(new File(args[0]));
        boolean exitFlag = false;

        /*
        while(dataLoaded && !exitFlag){
            Welcome page
            LoginModule loginModule = new LoginModule();
            exitFlag = loginModule.run();
            User user = loginModule.getUser();

            if(user.isStaff)
                StaffModule
            else
                MoviegoerModule
        }
        */

        //UpdateFile()
        updateFile(new File(args[0]));
        return;
    }

    private static boolean readFile(File file)throws IOException{
        Scanner sc = new Scanner(file);
        String oneLineData="";
        String[] oneLineDataArray;
        ArrayList<String> userNameList = new ArrayList<>();
        ArrayList<String> passWordList = new ArrayList<>();
        ArrayList<Boolean> userTypeList = new ArrayList<>();
        try {
            while (sc.hasNext()) {
                oneLineData = sc.nextLine();
                if (oneLineData.contains("<USER>")) {
                    oneLineData = sc.nextLine();
                    oneLineDataArray = oneLineData.split(";");
                    while (!oneLineData.contains("</USER>")) {
                        userNameList.add(oneLineDataArray[0]);
                        userTypeList.add(oneLineDataArray[1].equals("Staff"));
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
                            usersList.add(new User(userNameList.get(i), passWordList.get(i),userTypeList.get(i)));
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
                        if(oneLineDataArray.length==6){

                            bookingsList.add(new Booking(
                               oneLineDataArray[0],
                               oneLineDataArray[1],
                               oneLineDataArray[2],
                               oneLineDataArray[3],
                               new CinemaDate(
                                       (Integer.parseInt(oneLineDataArray[4].substring(0,4))-1900),
                                       (Integer.parseInt(oneLineDataArray[4].substring(4,6))-1),
                                       Integer.parseInt(oneLineDataArray[4].substring(6,8)),
                                       Integer.parseInt(oneLineDataArray[4].substring(8,10)),
                                       Integer.parseInt(oneLineDataArray[4].substring(10,12))),
                               Double.parseDouble(oneLineDataArray[5])
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
        for(User u:usersList){
            fileWriter.write(u.getUserName()+(u.isStaff()?";Staff"+System.lineSeparator():";MovieGoer"+System.lineSeparator()));
        }
        fileWriter.write("</USER>"+System.lineSeparator());

        fileWriter.write("<PASSWORD>"+System.lineSeparator());
        for(User u:usersList){
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

            fileWriter.write(b.getBookedTranscationID()+";"+b.getBookedMovieName()+";"+b.getBookedCinemaName()+
                            ";"+b.getBookedMovieGoerName()+";"+b.getBookedSellDate().getSellDate()+";"+b.getBookedPrice()+System.lineSeparator());
        }
        fileWriter.write("</BOOKING>");

        fileWriter.close();
    }
}
