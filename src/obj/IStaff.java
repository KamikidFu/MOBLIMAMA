package obj;

import java.io.IOException;
import java.text.ParseException;

public interface IStaff extends IUser {
    //Methods
    void doAdminJob() throws IOException, ParseException;
}
