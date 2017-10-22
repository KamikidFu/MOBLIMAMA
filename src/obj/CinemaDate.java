package obj;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CinemaDate extends Date{
    private boolean isHoliday;

    public CinemaDate() {
        super();
        isHoliday = false;
    }

    public CinemaDate(int Year, int Month, int Date, int Hrs, int Min) {
        super(Year,Month,Date,Hrs,Min);
        isHoliday=false;
    }

    public  String convertDateToDay(){
        SimpleDateFormat date2DayFormat = new SimpleDateFormat( "E" );
        return date2DayFormat.format( this);
    }

    public boolean isHoliday() {
        return isHoliday;
    }

    public void setHoliday(boolean holiday) {
        isHoliday = holiday;
    }
}
