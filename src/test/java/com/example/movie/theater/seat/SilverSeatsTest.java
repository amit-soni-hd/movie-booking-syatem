package com.example.movie.theater.seat;

import com.example.movie.theater.model.BookSeat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class SilverSeatsTest {

    private SilverSeats silverSeats;

    @Before
    public void setUp() {
        this.silverSeats = new SilverSeats(75);
    }

    @Test
    public void seatReserveTest() {
        List<String> list = new ArrayList<>();
        list.add("1A");
        list.add("1B");

        silverSeats.seatReserve(list);
        List<String> seats = silverSeats.availableSeats();
        assertEquals(false, seats.contains("1A"));
    }

    @Test
    public void seatReleaseTest() {
        List<String> list = new ArrayList<>();
        list.add("1A");
        list.add("1B");

        silverSeats.seatReserve(list);
        silverSeats.seatRelease(list);
        List<String> seats = silverSeats.availableSeats();
        assertEquals(true, seats.contains("1A"));
    }

    @Test
    public void seatBookTest() {
        List<String> list = new ArrayList<>();
        list.add("1A");
        list.add("1B");

        BookSeat bookSeat = new BookSeat();
        Map<String, BookStatus> bookStatusMap = new HashMap<>();
        bookSeat.setSeats(list);
        bookSeat.setBookStatusMap(bookStatusMap);

        silverSeats.seatReserve(list);
        silverSeats.seatBook(bookSeat);
        List<String> seats = silverSeats.availableSeats();
        assertEquals(false, seats.contains("1A"));
    }

    @Test
    public void seatBookTest_1() {
        List<String> list = new ArrayList<>();
        list.add("1A");
        list.add("1B");

        BookSeat bookSeat = new BookSeat();
        Map<String, BookStatus> bookStatusMap = new HashMap<>();
        bookSeat.setSeats(list);
        bookSeat.setBookStatusMap(bookStatusMap);

        silverSeats.seatReserve(list);
        silverSeats.seatBook(bookSeat);

        silverSeats.seatBook(bookSeat);
        assertEquals(BookStatus.SEAT_NOT_BOOKED, bookStatusMap.get("1A"));

    }
}
