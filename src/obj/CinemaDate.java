package obj;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CinemaDate extends Date{
    private boolean isHoliday;
    private int year;
    private int month;
    private int date;
    private int hrs;
    private int min;

    public CinemaDate() {
        super();
        isHoliday = false;
    }

    public CinemaDate(boolean isHoliday, int year, int month, int date, int hrs, int min) {
        super(year-1900,month-1,date,hrs,min);
        this.isHoliday = isHoliday;
        this.year = year;
        this.month = month;
        this.date = date;
        this.hrs = hrs;
        this.min = min;
    }

    public CinemaDate(int Year, int Month, int Date, int Hrs, int Min) {
        super(Year-1900,Month-1,Date,Hrs,Min);
        this.year = Year;
        this.month = Month;
        this.date = Date;
        this.hrs = Hrs;
        this.min = Min;
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

    public String getSellDate(){
        String Month="";
        if((++month)<10)
            Month+=("0"+month);
        else
            Month = month+"";
        String Date="";
        if(date<10)
            Date += ("0"+date);
        else
            Date=date+"";
        String Hrs = "";
        if(hrs<10)
            Hrs+=("0"+hrs);
        else
            Hrs=hrs+"";
        String Min="";
        if(min<10)
            Min+="0"+min;
        else
            Min=min+"";

        return (year+1900)+Month+Date+Hrs+Min;
    }
}
