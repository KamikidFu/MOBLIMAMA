package module.admin;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import module.admin.controllers.StaffMgr;
import module.admin.views.SystemConfigView;
import module.admin.views.ModifyMovieView;
import module.admin.views.Top5ListView;
import obj.IStaff;
import obj.IUser;
import obj.Staff;

public class AdminModule {
    private static int mainChoice;
    private IUser staff;
    public AdminModule(IUser Staff){
        this.staff = Staff;
        StaffMgr.activateStaffMgr(this.staff);
    }



    public boolean run() throws IOException, ParseException {
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("-------------------------------------");
            System.out.println("Welcome Staff!");
            System.out.println("-------------------------------------");
            System.out.println("(1) Modify Movie List");
            System.out.println("(2) List Top 5 Movie Ranking");
            System.out.println("(3) System Configuration");
            System.out.println("(4) Terminate System");
            System.out.println("Enter the your choice: ");
            mainChoice = sc.nextInt();

            while (mainChoice > 4 || mainChoice <= 0) {
                System.out.println("You have entered an invalid number");
                System.out.println("Please re-enter your choice: ");
                mainChoice = sc.nextInt();
            }

            switch (mainChoice) {
                case 1:
                    ModifyMovieView.run();
                    break;
                case 2:
                    //can edit this to link to Moviegoer's List Top 5 Movie Listing -> same view
                    Top5ListView.ListTop5Main();
                    break;
                case 3:
                    SystemConfigView.SystemConfigMain();
                    break;
                case 4:
                    System.out.println("Terminating");
                    break;
            }

        } while (mainChoice < 4);

        return true;
    }//end of runAdminHome



}
