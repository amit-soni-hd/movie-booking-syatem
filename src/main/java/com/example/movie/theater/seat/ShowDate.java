package com.example.movie.theater.seat;

import com.example.movie.theater.show.EveningShow;
import com.example.movie.theater.show.MatineeShow;
import com.example.movie.theater.show.MorningShow;
import com.example.movie.theater.show.MovieShowTime;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ShowDate {

    private Map<LocalDate, MorningShow> localDateMorningShowMap;
    private Map<LocalDate, EveningShow> localDateEveningShowMap;
    private Map<LocalDate, MatineeShow> localDateMatineeShowMap;
    private CreateShows createShows;

    private static ShowDate showDate = new ShowDate();

    public static ShowDate getInstance() {
        if(showDate == null)
            showDate = new ShowDate();
        return showDate;
    }

    private ShowDate() {
        this.localDateEveningShowMap = new HashMap<>();
        this.localDateMatineeShowMap = new HashMap<>();
        this.localDateMorningShowMap = new HashMap<>();
        this.createShows = new CreateShows();
    }

    public ShowTime getShowTime(LocalDate localDate, MovieShowTime movieShowTime) {

        if (movieShowTime.equals(MovieShowTime.MORNING)) {
            if (localDateMorningShowMap.containsKey(localDate))
                return localDateMorningShowMap.get(localDate);
            else {
                localDateMorningShowMap.put(localDate, createShows.getMorningShow());
                return localDateMorningShowMap.get(localDate);
            }
        } else if(movieShowTime.equals(MovieShowTime.MATINEE)) {
            if (localDateMatineeShowMap.containsKey(localDate))
                return localDateMatineeShowMap.get(localDate);
            else {
                localDateMatineeShowMap.put(localDate, createShows.getMatineeShow());
                return localDateMatineeShowMap.get(localDate);
            }
        } else {
            if (localDateEveningShowMap.containsKey(localDate))
                return localDateEveningShowMap.get(localDate);
            else {
                localDateEveningShowMap.put(localDate, createShows.getEveningShow());
                return localDateEveningShowMap.get(localDate);
            }
        }


    }

}
