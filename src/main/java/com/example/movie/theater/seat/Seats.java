package com.example.movie.theater.seat;

import com.example.movie.theater.model.BookSeat;

import java.util.List;
import java.util.Map;

public interface Seats {
    List<String> availableSeats();
    Map<String, BookStatus> seatBook(BookSeat bookSeat);
    void seatReserve(List<String> seats);
    void seatRelease(List<String> seats);
}
