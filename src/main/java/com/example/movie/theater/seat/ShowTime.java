package com.example.movie.theater.seat;

import com.example.movie.theater.model.BookSeat;

import java.util.List;

public interface ShowTime {
    List<String> available();
    void bookTicket(BookSeat bookSeat);
    void reserveSeats(List<String> seats);
    void releaseSeats(List<String> seats);
    Seats seatType(SeatType seatType);
}
