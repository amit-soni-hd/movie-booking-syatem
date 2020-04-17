package com.example.movie.theater.show;

import com.example.movie.theater.model.BookSeat;
import com.example.movie.theater.seat.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class MatineeShowTest {

    private MatineeShow matineeShow;

    @Before
    public void setUp() {
        NormalSeats normalSeats = new NormalSeats(100);
        GoldSeats goldSeats = new GoldSeats(50);
        SilverSeats silverSeats = new SilverSeats(75);
        this.matineeShow = new MatineeShow(normalSeats, silverSeats, goldSeats);
    }

    @Test
    public void setType() {
        Seats seats = matineeShow.seatType(SeatType.NORMAL);
        assertEquals(100, seats.availableSeats().size());
    }

    @Test
    public void setType_1() {
        Seats seats = matineeShow.seatType(SeatType.SILVER);
        assertEquals(75, seats.availableSeats().size());
    }

    @Test
    public void setType_2() {
        Seats seats = matineeShow.seatType(SeatType.GOLD);
        assertEquals(50, seats.availableSeats().size());
    }

    @Test
    public void reserveSeatsTest() {
        List<String> list = new ArrayList<>();
        list.add("1A");
        list.add("1B");
        Seats seats = matineeShow.seatType(SeatType.NORMAL);
        matineeShow.reserveSeats(list);
        List<String> available = matineeShow.available();
        assertEquals("1C", available.get(0));
    }

    @Test
    public void reserveSeatsTest_1() {
        List<String> list = new ArrayList<>();
        list.add("1A");
        list.add("1B");
        Seats seats = matineeShow.seatType(SeatType.NORMAL);
        matineeShow.reserveSeats(list);
        matineeShow.releaseSeats(list);
        List<String> available = matineeShow.available();
        assertEquals("1A", available.get(0));
    }

    @Test
    public void bookTicket() {
        BookSeat bookSeat = new BookSeat();
        List<String> list = new ArrayList<>();

        list.add("1A");
        list.add("1B");

        bookSeat.setSeats(list);

        HashMap<String, BookStatus> bookStatusHashMap = new HashMap<>();
        bookSeat.setBookStatusMap(bookStatusHashMap);

        Seats seats = matineeShow.seatType(SeatType.NORMAL);
        matineeShow.reserveSeats(list);
        matineeShow.bookTicket(bookSeat);

        assertEquals(BookStatus.SEAT_BOOKED, bookStatusHashMap.get("1A"));
    }
}
