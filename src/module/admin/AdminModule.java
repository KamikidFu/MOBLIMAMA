package module.admin;

import java.io.IOException;
import java.text.ParseException;

import module.admin.controllers.StaffMgr;
import module.admin.views.AdminMainView;
import obj.interfaces.IUser;

/**
 * Boundary class of setting up admin module
 * @author ANG SHU LIANG
 * @author Fu, Yunhao
 * @author LEONG MEI HAN
 */
public class AdminModule {

    private IUser staff;

    /**
     * Constructor of generating admin module
     * @param Staff Pass in the signed in staff user and activate StaffMgr
     */
    public AdminModule(IUser Staff){
        this.staff = Staff;
        StaffMgr.activateStaffMgr(this.staff);
    }

    /**
     * Run method, the main method for each boundary classes of UI
     * @return The boolean value whether user terminate the system
     * @throws IOException
     * @throws ParseException
     */
    public boolean run() throws IOException, ParseException{
        System.out.println("Welcome! "+staff.getUserName());
        AdminMainView.run();
        return true;
    }


}
