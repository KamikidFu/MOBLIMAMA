package module.moviegoer;

import module.moviegoer.controllers.MovieGoerMgr;
import module.moviegoer.views.MovieGoerMainView;
import obj.IUser;

public class MovieGoerModule {
    private IUser movieGoer;

    public MovieGoerModule(IUser movieGoer){
        this.movieGoer = movieGoer;
        MovieGoerMgr.activateMovieGoerMgr(this.movieGoer);
    }

    public boolean run(){
        System.out.println("Welcome! "+movieGoer.getUserName());
        MovieGoerMainView.run();
        return true;
    }
}
