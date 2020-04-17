package com.example.movie.theater.provider;

import com.example.movie.theater.seat.SeatType;
import com.example.movie.theater.seat.ShowTime;
import com.example.movie.theater.show.MovieShowTime;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class TheaterTest {

    @Test
    public void getMovieShowTest() {
        Theater theater = Theater.getInstance();
        ShowTime movieShow = theater.getMovieShow(LocalDate.now(), MovieShowTime.EVENING);
        movieShow.seatType(SeatType.NORMAL);
        assertEquals(100, movieShow.available().size());
    }

    @Test
    public void getMovieShowTest_1() {
        Theater theater = Theater.getInstance();
        ShowTime movieShow = theater.getMovieShow(LocalDate.now(), MovieShowTime.EVENING);
        movieShow.seatType(SeatType.GOLD);
        assertEquals(50, movieShow.available().size());
    }

}