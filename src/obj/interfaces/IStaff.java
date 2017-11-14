package obj.interfaces;

import java.io.IOException;
import java.text.ParseException;
/**
 * IStaff is an interface to define the essential method for staff
 * @author Fu, Yunhao
 */
public interface IStaff extends IUser {
    //Methods
    void doAdminJob() throws IOException, ParseException;
}
