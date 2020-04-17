package com.example.movie.theater.seat;

import com.example.movie.theater.show.MorningShow;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CreateShowsTest {

    private CreateShows createShows;

    @Before
    public void setUp() {
        this.createShows = new CreateShows();
    }

    @Test
    public void getMorningShowTest() {
        MorningShow morningShow = createShows.getMorningShow();
        morningShow.seatType(SeatType.NORMAL);
        Assert.assertEquals(100, morningShow.available().size());
    }

    @Test
    public void getEveningShowTest() {
        MorningShow morningShow = createShows.getMorningShow();
        morningShow.seatType(SeatType.SILVER);
        Assert.assertEquals(75, morningShow.available().size());
    }

    @Test
    public void getMatineeShowTest() {
        MorningShow morningShow = createShows.getMorningShow();
        morningShow.seatType(SeatType.GOLD);
        Assert.assertEquals(50, morningShow.available().size());
    }
}
