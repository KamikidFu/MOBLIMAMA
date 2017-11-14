package module.moviegoer;

import module.moviegoer.controllers.MovieGoerMgr;
import module.moviegoer.views.MovieGoerMainView;
import obj.interfaces.IUser;

import java.io.IOException;
import java.text.ParseException;

/**
 * Main module class for moviegoer, the enter of system for moviegoer
 * @author  Fu, Yunhao
 */
public class MovieGoerModule {
    private IUser movieGoer;

    /**
     * Activate MovieGoerMgr
     * @param movieGoer The current user
     */
    public MovieGoerModule(IUser movieGoer){
        this.movieGoer = movieGoer;
        MovieGoerMgr.activateMovieGoerMgr(this.movieGoer);
    }

    /**
     * Run method
     * @return Boolean value whether the user terminate system
     * @throws IOException
     * @throws ParseException
     */
    public boolean run() throws IOException, ParseException {
        System.out.println("Welcome! "+movieGoer.getUserName());
        MovieGoerMainView.run();
        return true;
    }
}
