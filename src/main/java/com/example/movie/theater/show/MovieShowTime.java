package com.example.movie.theater.show;

import static java.util.Calendar.AM;
import static java.util.Calendar.PM;

public enum MovieShowTime {

    MORNING("10"+AM), MATINEE("3"+PM), EVENING("6"+PM);

    String showTime;

    MovieShowTime(String showTime) {
        this.showTime = showTime;
    }
}
