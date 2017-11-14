package obj;

import java.util.ArrayList;

/**
 * MovieOnScene entity class
 * @author Fu, Yunhao
 */
public class MovieOnScene{
    private Movie onSceneMovie;
    private Cinema onSceneCinema;
    private Cineplex onSceneCineplex;
    private CinemaDate onSceneShowTime;
    private ArrayList<String> bookedSeat;

    /**
     * Default constructor
     */
    public MovieOnScene(){
        this.onSceneCinema = null;
        this.onSceneMovie=null;
        this.onSceneCineplex = null;
        this.onSceneShowTime=null;
        bookedSeat = new ArrayList<>();
    }

    /**
     *  Full constructor
     * @param OnSceneMovie Movie object
     * @param OnSceneCinema Cinema object
     * @param OnSceneCineplex Cineplex object
     * @param OnSceneCinemaDate CinemaDate object
     */
    public MovieOnScene(Movie OnSceneMovie, Cinema OnSceneCinema, Cineplex OnSceneCineplex, CinemaDate OnSceneCinemaDate){
        this.onSceneMovie=OnSceneMovie;
        this.onSceneCinema=OnSceneCinema;
        this.onSceneCineplex=OnSceneCineplex;
        this.onSceneShowTime = OnSceneCinemaDate;
        bookedSeat = new ArrayList<>();
    }

    /**
     * Add booked seat
     * @param place string value of seat
     */
    public void addBookedSeat(String place){
        bookedSeat.add(place);
    }

    /**
     *  return movie on scene
     * @return movie object
     */
    public Movie getOnSceneMovie() {
        return onSceneMovie;
    }

    /**
     * return cinema on scene
     * @return cinema object
     */
    public Cinema getOnSceneCinema() {
        return onSceneCinema;
    }

    /**
     * return cineplex on scene
     * @return cineplex object
     */
    public Cineplex getOnSceneCineplex() {
        return onSceneCineplex;
    }

    /**
     * return on scene show time
     * @return cinema date object
     */
    public CinemaDate getOnSceneShowTime() {
        return onSceneShowTime;
    }

    /**
     * return list of booked seat
     * @return list of booked seat
     */
    public ArrayList<String> getBookedSeat() {
        return bookedSeat;
    }

    /**
     * print all seat with availability
     */
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
