package obj;

import module.moviegoer.MovieGoerModule;

public class MovieGoer implements IMovieGoer {
    private String username;
    private String password;
    private MovieGoerModule movieGoerModule;

    public MovieGoer(String Username, String Password){
        this.username=Username;
        this.password=Password;
        movieGoerModule = new MovieGoerModule(this);
    }

    @Override
    public void doMovieGoerJob() {
        movieGoerModule.run();
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
