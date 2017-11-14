package module.login;

import app.Main;
import obj.interfaces.IUser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Login module is to verify user login information and determine the roll of user
 * @author Fu, Yunhao
 * @author Yuan Kae
 */
public class LoginModule {
    private static ArrayList<IUser> IUserArrayList = Main.getUsersList();
    private static Scanner scanner = Main.getSystemScanner();
    private String username;
    private String password;

    /**
     * Default constructor
     */
    public LoginModule(){}

    /**
     * Full constructor
     * @param Username Username typed by user for identification
     * @param Password Password typed by user for verification
     */
    public LoginModule(String Username, String Password){
        this.username=Username;
        this.password=Password;
    }

    /**
     * Run method, main method in login module to verify user
     * @return User who is been identified
     * @throws IOException
     */
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

    /**
     * Verify method, to verify the user by a list of users
     * @param username Username typed by user for identification
     * @param password Password typed by user for verification
     * @return
     */
    private static IUser verify(String username, String password){
        for(IUser u: IUserArrayList){
            if(u.getUserName().equals(username) && u.getPassword().equals(password))
                return u;
        }
        return null;
    }
}
