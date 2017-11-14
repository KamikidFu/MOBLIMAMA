package obj;

import module.moviegoer.MovieGoerModule;
import obj.interfaces.IMovieGoer;
/**
 * Moviegoer is a entity class
 * @author Fu, Yunhao
 */
public class MovieGoer implements IMovieGoer {
    private String username;
    private String password;
    private MovieGoerModule movieGoerModule;

    /**
     * Full constructor
     * @param Username username
     * @param Password password
     */
    public MovieGoer(String Username, String Password){
        this.username=Username;
        this.password=Password;
    }

    /**
     * Implement the interface method
     */
    @Override
    public void doMovieGoerJob() {
        try {
            movieGoerModule = new MovieGoerModule(this);
            movieGoerModule.run();
        }catch (Exception e){

        }
    }

    /**
     * Implement the interface method
     */
    @Override
    public String getUserName() {
        return this.username;
    }

    /**
     * Implement the interface method
     */
    @Override
    public String getPassword() {
        return this.password;
    }
}
