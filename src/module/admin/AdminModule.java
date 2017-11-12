package module.admin;

import java.io.IOException;
import java.text.ParseException;

import module.admin.controllers.StaffMgr;
import module.admin.views.AdminMainView;
import obj.IUser;

public class AdminModule {

    private IUser staff;
    public AdminModule(IUser Staff){
        this.staff = Staff;
        StaffMgr.activateStaffMgr(this.staff);
    }

    public boolean run() throws IOException, ParseException{
        System.out.println("Welcome! "+staff.getUserName());
        AdminMainView.run();
        return true;
    }


}
