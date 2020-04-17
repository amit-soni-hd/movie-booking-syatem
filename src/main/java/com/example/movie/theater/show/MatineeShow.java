package com.example.movie.theater.show;

import com.example.movie.theater.model.BookSeat;
import com.example.movie.theater.seat.*;

import java.util.List;

public class MatineeShow implements ShowTime {

    private NormalSeats normalSeats;
    private SilverSeats silverSeats;
    private GoldSeats goldSeats;

    private Seats seats;

    public MatineeShow(NormalSeats normalSeats, SilverSeats silverSeats, GoldSeats goldSeats) {
        this.normalSeats = normalSeats;
        this.silverSeats = silverSeats;
        this.goldSeats = goldSeats;
    }

    public Seats seatType(SeatType seatType) {

        if(seatType.equals(SeatType.NORMAL))
            seats = normalSeats;
        else if(seatType.equals(SeatType.SILVER))
            seats = silverSeats;
        else
            seats = goldSeats;
        return seats;
    }

    @Override
    public List<String> available() {
        return seats.availableSeats();
    }

    @Override
    public void bookTicket(BookSeat bookSeat) {
        seats.seatBook(bookSeat);
    }

    @Override
    public void reserveSeats(List<String> list) {
        seats.seatReserve(list);
    }

    @Override
    public void releaseSeats(List<String> list) {
        seats.seatRelease(list);
    }
}
