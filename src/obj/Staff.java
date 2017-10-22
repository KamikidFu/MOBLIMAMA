package obj;

public class Staff extends User {
    public Staff() {
        super("","",true);
    }

    public Staff(String Username, String Password){
        super(Username,Password,true);
    }
}
