package com.example.movie.theater.seat;

import com.example.movie.theater.show.MovieShowTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ShowDateTest {

    private ShowDate showDate;

    @Before
    public void setUp() {
        this.showDate = ShowDate.getInstance();
    }

    @Test
    public void getShowTimeTest() {
        ShowTime showTime = showDate.getShowTime(LocalDate.now(), MovieShowTime.MORNING);
        showTime.seatType(SeatType.NORMAL);
        List<String> available = showTime.available();
        Assert.assertEquals(100, available.size());
    }

    @Test
    public void getShowTimeTest_1() {
        ShowTime showTime = showDate.getShowTime(LocalDate.of(2020, 12, 12), MovieShowTime.MORNING);
        ShowTime showTime1 = showDate.getShowTime(LocalDate.of(2020, 12, 12), MovieShowTime.MORNING);
        showTime1.seatType(SeatType.NORMAL);
        List<String> available1 = showTime1.available();
        Assert.assertEquals(100, available1.size());
    }

    @Test
    public void getShowTimeTest_2() {
        ShowTime showTime = showDate.getShowTime(LocalDate.now(), MovieShowTime.MATINEE);
        showTime.seatType(SeatType.NORMAL);
        List<String> available = showTime.available();
        Assert.assertEquals(100, available.size());
    }

    @Test
    public void getShowTimeTest_3() {
        ShowTime showTime = showDate.getShowTime(LocalDate.of(2020, 12, 12), MovieShowTime.MATINEE);
        ShowTime showTime1 = showDate.getShowTime(LocalDate.of(2020, 12, 12), MovieShowTime.MATINEE);
        showTime1.seatType(SeatType.NORMAL);
        List<String> available1 = showTime1.available();
        Assert.assertEquals(100, available1.size());
    }

    @Test
    public void getShowTimeTest_4(){
        ShowTime showTime = showDate.getShowTime(LocalDate.now(), MovieShowTime.EVENING);
        showTime.seatType(SeatType.GOLD);
        List<String> available = showTime.available();
        Assert.assertEquals(50, available.size());
    }

    @Test
    public void getShowTimeTest_5() {
        ShowTime showTime = showDate.getShowTime(LocalDate.of(2020, 12, 12), MovieShowTime.EVENING);
        ShowTime showTime1 = showDate.getShowTime(LocalDate.of(2020, 12, 12), MovieShowTime.EVENING);
        showTime1.seatType(SeatType.NORMAL);
        List<String> available1 = showTime1.available();
        Assert.assertEquals(100, available1.size());
    }
}
