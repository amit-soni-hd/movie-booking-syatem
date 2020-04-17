package com.example.movie.theater.provider;

import com.example.movie.theater.seat.ShowDate;
import com.example.movie.theater.seat.ShowTime;
import com.example.movie.theater.show.MovieShowTime;

import java.time.LocalDate;

public class Theater {

    private static Theater theater = new Theater();

    private ShowDate showDate;

    public ShowTime getMovieShow(LocalDate localDate, MovieShowTime movieShowTime) {
        return showDate.getShowTime(localDate, movieShowTime);
    }

    private Theater() {
        this.showDate = ShowDate.getInstance();
    }

    public static Theater getInstance() {
        if(theater == null)
            theater = new Theater();
        return theater;
    }

}
