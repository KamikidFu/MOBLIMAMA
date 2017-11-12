package obj;

import module.moviegoer.MovieGoerModule;

import java.io.IOException;

public class MovieGoer implements IMovieGoer {
    private String username;
    private String password;
    private MovieGoerModule movieGoerModule;

    public MovieGoer(String Username, String Password){
        this.username=Username;
        this.password=Password;
    }

    @Override
    public void doMovieGoerJob() {
        try {
            movieGoerModule = new MovieGoerModule(this);
            movieGoerModule.run();
        }catch (Exception e){

        }
    }

    @Override
    public String getUserName() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }
}
