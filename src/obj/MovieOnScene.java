package obj;

import java.util.ArrayList;

public class MovieOnScene{
    private Movie onSceneMovie;
    private Cinema onSceneCinema;
    private Cineplex onSceneCineplex;
    private CinemaDate onSceneShowTime;
    private ArrayList<String> bookedSeat;

    public MovieOnScene(){
        this.onSceneCinema = null;
        this.onSceneMovie=null;
        this.onSceneCineplex = null;
        this.onSceneShowTime=null;
        bookedSeat = new ArrayList<>();
    }

    public MovieOnScene(Movie OnSceneMovie, Cinema OnSceneCinema, Cineplex OnSceneCineplex, CinemaDate OnSceneCinemaDate){
        this.onSceneMovie=OnSceneMovie;
        this.onSceneCinema=OnSceneCinema;
        this.onSceneCineplex=OnSceneCineplex;
        this.onSceneShowTime = OnSceneCinemaDate;
        bookedSeat = new ArrayList<>();
    }

    public void addBookedSeat(String place){
        bookedSeat.add(place);
    }

    public int getRemainingTicketAmount(){
        int[][] seat = onSceneCinema.getSeatMap();
        return (seat.length*seat[0].length)-bookedSeat.size();
    }

    public Movie getOnSceneMovie() {
        return onSceneMovie;
    }

    public Cinema getOnSceneCinema() {
        return onSceneCinema;
    }

    public Cineplex getOnSceneCineplex() {
        return onSceneCineplex;
    }

    public CinemaDate getOnSceneShowTime() {
        return onSceneShowTime;
    }

    public ArrayList<String> getBookedSeat() {
        return bookedSeat;
    }

    public void printSeat() {
        int[][] tempBookedSeat = new int[this.onSceneCinema.getSeatMap().length][this.onSceneCinema.getSeatMap()[0].length];
        for(String s: bookedSeat){
            String[] oneRecord = s.split(",");
            tempBookedSeat[Integer.parseInt(oneRecord[0])][Integer.parseInt(oneRecord[1])-1]=1;
        }
        String oneRowData=" \t";
        for(int i=0;i<tempBookedSeat.length;i++){
            if(i!=0)
                oneRowData=i+"\t";
            for(int j=0;j<tempBookedSeat[0].length;j++){
                if(i==0) {
                    oneRowData += (j + 1) + "\t";
                    continue;
                }
                if(tempBookedSeat[i][j]==1)
                    oneRowData+="×\t";
                else
                    oneRowData+="○\t";
            }
            System.out.println(oneRowData);
            oneRowData="";
        }
    }
}
