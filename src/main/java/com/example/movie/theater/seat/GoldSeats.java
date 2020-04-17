package com.example.movie.theater.seat;

import com.example.movie.theater.model.BookSeat;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class GoldSeats implements Seats {

    private int totalSeat;
    private int price;
    Map<String, Boolean> availableSeats;
    Map<String, BookSeat> bookedSeat;

    public GoldSeats(int totalSeat) {
        this.totalSeat = totalSeat;
        this.price = 750;
        this.availableSeats = new ConcurrentHashMap<>(totalSeat);
        this.bookedSeat = new ConcurrentHashMap<>(totalSeat);
        generateSeat();
    }

    public synchronized void seatReserve(List<String> seats) {
        seats.forEach( (seat) -> availableSeats.put(seat, false));
    }

    public void seatRelease(List<String> seats) {
        seats.forEach( (seat) -> availableSeats.put(seat, true));
    }

    private void generateSeat() {
        for (int i = 8; i <= 9; i++) {
            for(int j = 1; j <=25 ; j++ ) {
                String seatNo = i+""+(char)(j+64);
                availableSeats.put(seatNo, true);
            }
        }
    }

    @Override
    public List<String> availableSeats() {

        return availableSeats
                .entrySet()
                .stream()
                .filter( (seat) -> isAvailable(seat.getKey()))
                .map( (seat) -> seat.getKey())
                .collect(Collectors.toList());

    }

    private boolean isAvailable(String seatNo) {
        return availableSeats.get(seatNo);
    }

    @Override
    public synchronized Map<String, BookStatus> seatBook(BookSeat bookSeat) {
        bookSeat.getSeats()
                .forEach( (seat) ->
                {
                    if(!bookedSeat.containsKey(seat)) {
                        bookedSeat.put(seat, bookSeat);
                        bookSeat.getBookStatusMap().put(seat, BookStatus.SEAT_BOOKED);
                    } else
                        bookSeat.getBookStatusMap().put(seat, BookStatus.SEAT_NOT_BOOKED);

                });
        return bookSeat.getBookStatusMap();
    }
}
