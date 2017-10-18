package app;

import module.*;
import obj.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    ArrayList<Cineplex> cineplexes = new ArrayList<>();
    //static User user;

    public static void main(String[] args)throws IOException{
        if (args.length != 1)
            return;
        readFile(new File(args[0]));
        LoginModule lm = new LoginModule();
    }
    private static void readFile(File file)throws IOException{
        Scanner sc = new Scanner(file);
        String onelineData="";
        String userName="";
        String userPass="";
        while(sc.hasNext()){
            onelineData = sc.nextLine();
            if(onelineData.contains("User:")){
                userName=sc.nextLine();
            }else if(onelineData.contains("Password:")){
                userPass=sc.nextLine();
                //user = new User(userName, userPass);
            }
        }
    }
}
