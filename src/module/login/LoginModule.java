package module.login;

import app.Main;
import obj.IUser;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LoginModule {
    private static ArrayList<IUser> IUserArrayList = Main.getUsersList();
    private static Scanner scanner = Main.getSystemScanner();
    //private static BufferedReader bufferedReader = Main.getSystemBufferedReader();
    private String username;
    private String password;

    public LoginModule(){}

    public LoginModule(String Username, String Password){
        this.username=Username;
        this.password=Password;
    }

    public IUser run() throws IOException{
        System.out.print("Username:");
        this.username= scanner.nextLine();
        //Password
        System.out.print("Password:");
        this.password= scanner.nextLine();
        //verify()
        IUser logged = verify(this.username,this.password);
        //If true, return user
        //else, return NULL
        if(logged!=null){
            return logged;
        }else{
            return null;
        }
    }

    private static IUser verify(String username, String password){
        for(IUser u: IUserArrayList){
            if(u.getUserName().equals(username) && u.getPassword().equals(password))
                return u;
        }
        return null;
    }
}
