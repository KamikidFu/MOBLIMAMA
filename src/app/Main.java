package app;

import exception.IllegalDataException;
import module.admin.AdminModule;
import module.admin.controllers.StaffMgr;
import module.login.LoginModule;
import module.moviegoer.MovieGoerModule;
import module.moviegoer.controllers.MovieGoerMgr;
import obj.*;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.zip.CheckedInputStream;

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
    private static ArrayList<MovieOnScene> onScenesMovieList = new ArrayList<>();
    private static ArrayList<IUser> usersList = new ArrayList<>();
    //private static BufferedReader systemBufferedReader = new BufferedReader(new InputStreamReader(System.in));
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
        boolean dataLoaded =readFile();
        boolean exitFlag = false;

        while(dataLoaded && !exitFlag){

            System.out.println("\nWelcome to MOBLIMA System!");

            //LoginModule loginModule = new LoginModule();
            IUser temp =(new LoginModule()).run();

            if(temp == null){
                System.out.println("Error: Wrong User Information!\n");
                continue;
            }else if(temp instanceof MovieGoer){
                ((MovieGoer) temp).doMovieGoerJob();
                exitFlag = true;
            }else if (temp instanceof Staff){
                ((Staff) temp).doAdminJob();
                exitFlag = true;
            }
        }

        //Close all scanner which are using System.in
        //systemBufferedReader.close();
        systemScanner.close();

        if(updateFile()){
            System.out.println("Thank you for using our system!");
            return;
        }else {
            System.err.println("Failed to update data!");
            return;
        }

    }



    public static ArrayList<MovieOnScene> getOnScenesMovieList() {
        return onScenesMovieList;
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

    /*public static BufferedReader getSystemBufferedReader() {
        return systemBufferedReader;
    }*/

    public static Scanner getSystemScanner() {
        return systemScanner;
    }

    public static boolean tryParseInteger(String string){
        try{
            Integer.parseInt(string);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static int scannerIntegerInput(){
        int temp=0;
        String test="";
        test = systemScanner.nextLine();
        while (!Main.tryParseInteger(test)) {
            System.out.println("Wrong input please check!");
            test = systemScanner.nextLine();
        }
        return Integer.parseInt(test);
    }

    private static Movie findMovie(String name){
        for(Movie m:moviesList){
            if(m.getMovieName().contains(name)){
                return m;
            }
        }
        return null;
    }

    private static Cinema findCinemaByCode(String name){
        for(Cinema c:cinemasList){
            if(c.getCinemaCode().contains(name)){
                return c;
            }
        }
        return null;
    }

    private static Cineplex findCineplex(String name){
        for(Cineplex c: cineplexesList){
            if(c.getCineplexName().contains(name)){
                return c;
            }
        }
        return null;
    }

    private static MovieOnScene findMovieOnScene(Movie movie, Cinema cinema, Cineplex cineplex, CinemaDate cinemaDate){
        for(MovieOnScene m: onScenesMovieList){
            if(m.getOnSceneMovie().equals(movie) && m.getOnSceneCinema().equals(cinema) &&
                    m.getOnSceneCineplex().equals(cineplex) && m.getOnSceneShowTime().isSameTime(cinemaDate))
                return m;
        }
        return null;
    }

    private static Cinema findCinemaByName(String s) {
        for(Cinema c:cinemasList){
            if(c.getCinemaName().contains(s)){
                return c;
            }
        }
        return null;
    }
    private static boolean readFile(){
        try {
            readUser();
            readMovie();
            readCinema();
            readCineplex();
            readMovieInCinema();
            readMovieOnScenes();
            readReview();
            readBooking();
            readSysConfigure();
            return true;

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    private static void readSysConfigure() {
        try {
            Scanner localScanner = new Scanner(new File("src/res/SysCon.txt"));
            TicketPrice.setBasePrice(Double.parseDouble(localScanner.nextLine()));
            while(localScanner.hasNext()){
                CinemaDate.addHoliday(localScanner.nextLine());
            }
            localScanner.close();
        }catch (Exception e){
            System.err.println(e);
            e.printStackTrace();
        }
    }

    private static void readUser(){
        try{
            String[] onelinetemp;
            Scanner localScanner = new Scanner(new File("src/res/Users.txt"));
            while(localScanner.hasNext()){
                onelinetemp = localScanner.nextLine().split(";");
                if(onelinetemp.length==3){
                    if(onelinetemp[2].equals("Staff")){
                        usersList.add(new Staff(onelinetemp[0],onelinetemp[1]));
                    }else if(onelinetemp[2].equals("MovieGoer")){
                        usersList.add(new MovieGoer(onelinetemp[0],onelinetemp[1]));
                    }
                }else {
                    throw new IllegalDataException(
                            "Wrong user data retrieved from file"
                    );
                }
            }
            localScanner.close();
        }catch (Exception e){
            System.err.println(e);
            e.printStackTrace();
        }
    }

    private static void readMovie(){
        try{
            String[] onelinetemp;
            Scanner localScanner = new Scanner(new File("src/res/Movies.txt"));
            while (localScanner.hasNext()) {
                onelinetemp = localScanner.nextLine().split(";");
                if (onelinetemp.length >= 5) {
                    ArrayList<String> temp = new ArrayList<>();
                    for(int i=5; i<onelinetemp.length;i++){
                        temp.add(onelinetemp[i]);
                    }
                    moviesList.add(new Movie(
                            onelinetemp[0], onelinetemp[1],
                            onelinetemp[2], onelinetemp[3],
                            onelinetemp[4],temp));
                }else {
                    throw new IllegalDataException(
                            "Wrong movie data retrieved from file"
                    );
                }
            }
            localScanner.close();
        }catch (Exception e){
            System.err.println(e);
            e.printStackTrace();
        }
    }

    private static void readCinema(){
        try{
            String[] onelinetemp;
            Scanner localScanner = new Scanner(new File("src/res/Cinemas.txt"));
            while (localScanner.hasNext()) {
                onelinetemp = localScanner.nextLine().split(";");
                if (onelinetemp.length >= 3) {
                    if (onelinetemp.length == 4) {
                        String[] temp = onelinetemp[3].split(",");
                        cinemasList.add(new Cinema(
                                onelinetemp[0], onelinetemp[1], onelinetemp[2],
                                Integer.parseInt(temp[0]), Integer.parseInt(temp[1])));
                    } else {
                        String[] temp = onelinetemp[3].split(";");
                        cinemasList.add(new Cinema(
                                onelinetemp[0], onelinetemp[1],
                                onelinetemp[2],
                                Integer.parseInt(temp[0]), Integer.parseInt(temp[1])));
                    }
                } else {
                    throw new IllegalDataException(
                            "Wrong cinema data retrieved from file"
                    );
                }
            }
            localScanner.close();
        }catch (Exception e){
            System.err.println(e);
            e.printStackTrace();
        }
    }

    private static void readCineplex(){
        try{
            String[] onelinetemp;
            Scanner localScanner = new Scanner(new File("src/res/Cineplexs.txt"));
            while (localScanner.hasNext()) {
                onelinetemp = localScanner.nextLine().split(";");
                if (onelinetemp.length >= 2) {
                    if (onelinetemp.length == 2) {
                        cineplexesList.add(new Cineplex(
                                onelinetemp[0], onelinetemp[2]
                        ));
                    } else {
                        ArrayList<Cinema> tempCinemaList = new ArrayList<>();
                        for (int i = 2; i < onelinetemp.length; i++) {
                            tempCinemaList.add(findCinemaByName(onelinetemp[i]));
                        }
                        cineplexesList.add(new Cineplex(
                                onelinetemp[0], onelinetemp[1],
                                tempCinemaList
                        ));
                    }
                } else {
                    throw new IllegalDataException(
                            "Wrong cineplex data retrieved from file"
                    );
                }
            }
            localScanner.close();
        }catch (Exception e){
            System.err.println(e);
            e.printStackTrace();
        }
    }

    private static void readReview(){
        try{
            String[] onelinetemp;
            Scanner localScanner = new Scanner(new File("src/res/Reviews.txt"));
            while (localScanner.hasNextLine()) {
                onelinetemp = localScanner.nextLine().split(";");
                if (onelinetemp.length == 4) {
                    reviewsList.add(new Review(
                            Double.parseDouble(onelinetemp[0]),
                            onelinetemp[1],
                            onelinetemp[2], onelinetemp[3]));
                } else {
                    throw new IllegalDataException(
                            "Wrong review data retrieved from file"
                    );
                }
            }
            localScanner.close();
        }catch (Exception e){
            System.err.println(e);
            e.printStackTrace();
        }
    }

    private static void readMovieInCinema(){
        try{
            String[] onelinetemp;
            Scanner localScanner = new Scanner(new File("src/res/Movietimes.txt"));
            while (localScanner.hasNext()) {
                onelinetemp = localScanner.nextLine().split(";");
                if (onelinetemp.length > 3) {
                    Movie tempMovie = findMovie(onelinetemp[1]);
                    Cinema tempCinema = findCinemaByCode(onelinetemp[0]);
                    if (tempCinema != null && tempMovie != null) {
                        MovieInCinema tempMovieInCinema = new MovieInCinema(tempMovie, tempCinema);
                        for (int i = 2; i < onelinetemp.length; i++) {
                            tempMovieInCinema.addShowTime(CinemaDate.parseCinemaDate(onelinetemp[i]));
                        }
                        tempCinema.addMovieInCinema(tempMovieInCinema);
                    }
                } else {
                    throw new IllegalDataException(
                            "Wrong cineplex data retrieved from file"
                    );
                }
            }
            localScanner.close();
        }catch (Exception e){
            System.err.println(e);
            e.printStackTrace();
        }
    }

    private static void readMovieOnScenes(){
        try{
            String[] onelinetemp;
            Scanner localScanner = new Scanner(new File("src/res/MovieOnScenes.txt"));
            while (localScanner.hasNextLine()) {
                onelinetemp = localScanner.nextLine().split(";");
                if (onelinetemp.length > 1) {
                    Cinema tempCinema = findCinemaByCode(onelinetemp[1]);
                    Movie tempMovie = findMovie(onelinetemp[0]);
                    Cineplex tempCineplex = findCineplex(onelinetemp[2]);
                    CinemaDate tempCinemaDate = CinemaDate.parseCinemaDate(onelinetemp[3]);
                    onScenesMovieList.add(new MovieOnScene(tempMovie, tempCinema, tempCineplex, tempCinemaDate));
                } else {
                    throw new IllegalDataException(
                            "Wrong review data retrieved from file"
                    );
                }
            }
            localScanner.close();
        }catch (Exception e){
            System.err.println(e);
            e.printStackTrace();
        }
    }

    private static void readBooking(){
        try{
            String[] onelinetemp;
            Scanner localScanner = new Scanner(new File("src/res/Bookings.txt"));
            while (localScanner.hasNextLine()) {
                onelinetemp = localScanner.nextLine().split(";");
                if (onelinetemp.length >= 9) {
                    int[] tempTicketInfo = new int[3];
                    Movie tempMovie = findMovie(onelinetemp[1]);
                    Cinema tempCinema = findCinemaByName(onelinetemp[2]);
                    Cineplex tempCineplex = findCineplex(onelinetemp[3]);
                    CinemaDate tempCinemaDate = CinemaDate.parseCinemaDate(onelinetemp[4]);
                    MovieOnScene tempMovieOnScene = findMovieOnScene(tempMovie,tempCinema,tempCineplex,tempCinemaDate);
                    String[] tempTicketInfoData = onelinetemp[8].split(",");
                    ArrayList<String> tempBookedSeat = new ArrayList<>();
                    for (int i = 0; i < 3; i++)
                        tempTicketInfo[i] = Integer.parseInt(tempTicketInfoData[i]);
                    for (int i = 9; i < (tempTicketInfo[0] + tempTicketInfo[1] + tempTicketInfo[2] + 9); i++) {
                        tempMovieOnScene.addBookedSeat(onelinetemp[i]);
                        tempBookedSeat.add(onelinetemp[i]);
                    }
                    Booking booking = new Booking(onelinetemp[0], tempMovieOnScene, onelinetemp[5],
                            CinemaDate.parseCinemaDate(onelinetemp[6]), Double.parseDouble(onelinetemp[7]),
                            tempTicketInfo, tempBookedSeat);
                    bookingsList.add(booking);
                } else {
                    throw new IllegalDataException(
                            "Wrong booking data retrieved from file"
                    );
                }
            }
            localScanner.close();
        }catch (Exception e){
            System.err.println(e);
            e.printStackTrace();
        }
    }


    private static boolean updateFile(){
        try{
            updateUser();
            updateMovie();
            updateCinema();
            updateCineplex();
            updateReview();
            updateMovieInCinema();
            updateMovieOnScenes();
            updateBooking();
            updateSysConfigure();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    private static void updateSysConfigure() {
        try{
            FileWriter fileWriter = new FileWriter(new File("src/res/SysCon.txt"), false);
            fileWriter.write(""+TicketPrice.getBasePrice()+System.lineSeparator());
            for(String s: CinemaDate.getHolidayList()){
                fileWriter.write(s+System.lineSeparator());
            }
            fileWriter.close();
        }catch (Exception e){
            System.err.println(e);
            e.printStackTrace();
        }
    }

    private static void updateUser() {
        try {
            FileWriter fileWriter = new FileWriter(new File("src/res/Users.txt"), false);
            for (IUser u : usersList) {
                fileWriter.write(u.getUserName() +";"+ u.getPassword() +
                        (u instanceof Staff ? ";Staff" : ";MovieGoer") +
                        System.lineSeparator());
            }
            fileWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static void updateMovie(){
        try {
            FileWriter fileWriter = new FileWriter(new File("src/res/Movies.txt"), false);
            for(Movie m:moviesList){
                ArrayList<String> cast = m.getMovieCastList();
                String castString="";
                for(int i=0;i<cast.size();i++){
                    castString+=";";
                    castString+=cast.get(i);
                }
                fileWriter.write(m.getMovieName()+";"+m.getMovieType()+";"+m.getMovieStatus()+
                        ";"+m.getMovieSynopsis()+";"+m.getMovieDirector()+castString+System.lineSeparator());
            }
            fileWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static void updateCinema(){
        try{
            FileWriter fileWriter = new FileWriter(new File("src/res/Cinemas.txt"), false);
            for(Cinema c:cinemasList){
                int[][] seatMap = c.getSeatMap();
                fileWriter.write(c.getCinemaName()+";"+c.getCinemaClass()+";"+c.getCinemaCode()+";"+seatMap.length+","+seatMap[0].length+System.lineSeparator());
            }
            fileWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static void updateCineplex(){
        try{
            FileWriter fileWriter = new FileWriter(new File("src/res/Cineplexs.txt"), false);
            for(Cineplex c:cineplexesList){
                String cinema="";
                for(Cinema ci:c.getCinemaList()){
                    cinema += (";"+ci.getCinemaName());
                }
                fileWriter.write(c.getCineplexName()+";"+c.getCineplexLocation()+cinema+System.lineSeparator());
            }
            fileWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static void updateReview(){
        try{
            FileWriter fileWriter = new FileWriter(new File("src/res/Reviews.txt"), false);
            for (Review r:reviewsList){
                fileWriter.write(r.getRate()+";"+r.getMovieGoerName()+";"+r.getMovieName()+";"+r.getComment()+System.lineSeparator());
            }
            fileWriter.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    private static void updateMovieInCinema(){
        try{
            FileWriter fileWriter = new FileWriter(new File("src/res/Movietimes.txt"), false);
            for (Cinema c:cinemasList){
                for(MovieInCinema m:c.getMoviesList()){
                    ArrayList<CinemaDate> temp = m.getInCinemaShowTimes();
                    String showtime="";
                    for(CinemaDate cd:temp)
                        showtime+=(";"+cd.getCinemaDate());
                    fileWriter.write(c.getCinemaCode()+";"+m.getInCinemaMovie().getMovieName()+
                            showtime+System.lineSeparator());
                }
            }
            fileWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static void updateMovieOnScenes(){
        try{
            FileWriter fileWriter = new FileWriter(new File("src/res/MovieOnScenes.txt"), false);
            for(MovieOnScene m: onScenesMovieList){
                fileWriter.write(m.getOnSceneMovie().getMovieName()+";"+m.getOnSceneCinema().getCinemaCode()+
                        ";"+m.getOnSceneCineplex().getCineplexName()+";"+m.getOnSceneShowTime().getCinemaDate()+System.lineSeparator());
            }
            fileWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static void updateBooking(){
        try{
            FileWriter fileWriter = new FileWriter(new File("src/res/Bookings.txt"), false);
            for(Booking b:bookingsList){
                MovieOnScene temp = b.getBookedMovieOnScene();
                ArrayList<String> tempSeat = b.getBookedSeat();
                String tempSeatOutput="";
                for(String s: tempSeat){
                    tempSeatOutput+=(";"+s);
                }
                String output = b.getBookedTransactionID()+";"+temp.getOnSceneMovie().getMovieName()+";"+temp.getOnSceneCinema().getCinemaName()+
                        ";"+temp.getOnSceneCineplex().getCineplexName()+";"+temp.getOnSceneShowTime().getCinemaDate()+";"+
                        b.getBookedMovieGoerName()+";"+b.getBookedSellDate().getCinemaDate()+";"+b.getBookedPrice()+";"+
                        b.getTicketinfo()[0]+","+b.getTicketinfo()[1]+","+b.getTicketinfo()[2]+tempSeatOutput+System.lineSeparator();
                fileWriter.write(output);
            }
            fileWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
