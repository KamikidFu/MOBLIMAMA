package app;

import exception.IllegalDataException;
import module.*;
import obj.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
    private static ArrayList<User> usersList = new ArrayList<>();
    private static ArrayList<Movie> moviesList = new ArrayList<>();
    private static ArrayList<Cinema> cinemasList = new ArrayList<>();
    private static ArrayList<Cineplex> cineplexesList = new ArrayList<>();
    private static ArrayList<Review> reviewsList = new ArrayList<>();
    private static ArrayList<Booking> bookingsList = new ArrayList<>();

    public static void main(String[] args)throws IOException{
        if (args.length != 1)
            return;
        boolean dataLoaded =false;
        boolean exitFlag = false;
        dataLoaded = readFile(new File(args[0]));
        if(dataLoaded) {
            System.out.println("Data Successfully loaded!");
        }
        else {
            System.out.println("Loading data failed!");
            return;
        }
        while(dataLoaded && !exitFlag){
            LoginModule loginModule = new LoginModule();
            exitFlag = loginModule.run();
        }

        //UpdateFile()
        return;
    }


    private static boolean readFile(File file)throws IOException{
        Scanner sc = new Scanner(file);
        String oneLineData="";
        String[] oneLineDataArray;
        ArrayList<String> userNameList = new ArrayList<>();
        ArrayList<String> passWordList = new ArrayList<>();
        try {
            while (sc.hasNext()) {
                oneLineData = sc.nextLine();
                if (oneLineData.contains("<USER>")) {
                    oneLineData = sc.nextLine();
                    while (!oneLineData.contains("</USER>")) {
                        userNameList.add(oneLineData);
                        oneLineData = sc.nextLine();
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
                            usersList.add(new User(userNameList.get(i), passWordList.get(i)));
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
}
