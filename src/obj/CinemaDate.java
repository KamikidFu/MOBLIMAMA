package obj;

import app.Main;
import com.sun.xml.internal.fastinfoset.util.CharArray;
import module.moviegoer.controllers.MovieGoerMgr;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CinemaDate extends Date{
    private int year;
    private int month;
    private int date;
    private int hrs;
    private int min;
    private static ArrayList<String> holidayList = new ArrayList<>();

    public CinemaDate() {
        super();
    }

    public CinemaDate(int year, int month, int date, int hrs, int min) {
        super(year-1900,month-1,date,hrs,min);
        this.year = year;
        this.month = month;
        this.date = date;
        this.hrs = hrs;
        this.min = min;
    }

    /*public  String convertDateToDay(){
        SimpleDateFormat date2DayFormat = new SimpleDateFormat( "E" );
        return date2DayFormat.format( this);
    }*/

 /*   public boolean isSameDate(String date){
        if(this.year == Integer.parseInt(date.substring(0,3)) && this.month == Integer.parseInt(date.substring(4,5)) && this.date == Integer.parseInt(date.substring(6,7))){
            return true;
        }
        return false;
    }*/

    public boolean isSameTime(CinemaDate cinemaDate){
        if(this.year==cinemaDate.year && this.month == cinemaDate.month &&
                this.date==cinemaDate.date && this.hrs == cinemaDate.hrs &&
                this.min == cinemaDate.min)
            return true;
        return false;
    }


    public static boolean isHoliday(CinemaDate cinemaDate) {
        return  holidayList.contains(cinemaDate.getCinemaDate());
    }
    public static void addHoliday(String cinemaDate){holidayList.add(cinemaDate);}


    public static CinemaDate parseCinemaDate(String string){
        if(string.toCharArray().length==12 && isAllDigit(string)) {
            return new CinemaDate(
                    (Integer.parseInt(string.substring(0, 4))),
                    (Integer.parseInt(string.substring(4, 6))),
                    Integer.parseInt(string.substring(6, 8)),
                    Integer.parseInt(string.substring(8, 10)),
                    Integer.parseInt(string.substring(10, 12)));
        }else if(string.contains(":")&&string.split(":").length==5){
            String[] temp = string.split(":");
            return new CinemaDate(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]),
                    Integer.parseInt(temp[2]),Integer.parseInt(temp[3]),Integer.parseInt(temp[4]));
        }
        return null;
    }

    public String getCinemaDate(){
        String Month="";
        if((month)<10)
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

        return (year)+(Month)+Date+Hrs+Min;
    }

    private static boolean isAllDigit(String string){
        char[] charArray = string.toCharArray();
        for(int i=0;i<charArray.length;i++){
            if(Main.tryParseInteger(String.valueOf(charArray[i]))){
                continue;
            }else {
                return false;
            }
        }
        return true;
    }

    public static ArrayList<String> getHolidayList() {
        return holidayList;
    }
}
