package obj;

import module.admin.AdminModule;

import java.io.IOException;
import java.text.ParseException;

public class Staff implements IStaff {
    private String username;
    private String password;
    private AdminModule adminModule;

    public Staff(String Username, String Password){
        this.username=Username;
        this.password=Password;
        adminModule = new AdminModule(this);
    }

    @Override
    public void doAdminJob() throws IOException, ParseException {
        adminModule.run();
    }

    @Override
    public String getUserName() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }
}
