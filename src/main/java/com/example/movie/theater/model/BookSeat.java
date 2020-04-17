package com.example.movie.theater.model;

import com.example.movie.theater.payment.PaymentStatus;
import com.example.movie.theater.seat.BookStatus;
import com.example.movie.theater.seat.SeatType;
import com.example.movie.theater.show.MovieShowTime;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
public class BookSeat {

    private String bookingId;
    private MovieShowTime movieShowTime;
    private LocalDate showDate;
    private SeatType seatType;
    private List<String> seats;
    private User user;
    private PaymentStatus paymentStatus;
    private Map<String, BookStatus> bookStatusMap;

}
