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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStaff() {
        return isStaff;
    }

    public void setStaff(boolean staff) {
        isStaff = staff;
    }
}
