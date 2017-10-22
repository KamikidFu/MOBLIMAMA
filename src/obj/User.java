package obj;

public class User {
    protected String userName;
    protected String password;
    protected boolean isStaff;

    public User( String UserName,String Password) {
        this.password = Password;
        this.userName = UserName;
        isStaff=false;
    }
    public User(String UserName,String Password, boolean IsStaff) {
        this.password = Password;
        this.userName = UserName;
        isStaff=IsStaff;
    }
}
