package module.moviegoer.views;


import app.Main;
import module.moviegoer.controllers.MovieGoerMgr;

import java.io.IOException;

public class CreateReviewView {
    private static MovieGoerMgr movieGoerMgr = MovieGoerMgr.getMovieGoerMgr();

    public static void run() throws IOException {
        System.out.println("Reviewable Movies---------");
        movieGoerMgr.reviewMovie();
        return;
    }
}