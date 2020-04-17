package com.example.movie.theater.booking;

import com.example.movie.theater.model.BookSeat;
import com.example.movie.theater.model.User;
import com.example.movie.theater.payment.PaymentResponse;
import com.example.movie.theater.payment.PaymentService;
import com.example.movie.theater.payment.PaymentStatus;
import com.example.movie.theater.provider.GenerateBookingId;
import com.example.movie.theater.provider.Theater;
import com.example.movie.theater.seat.*;
import com.example.movie.theater.show.MovieShowTime;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class TicketBooking implements Runnable {

    private Theater theater = Theater.getInstance();

    @Override
    public void run() {

        BufferedReader in =
                new BufferedReader(new InputStreamReader(System.in));
        User user = getUser(in);


        log.info("Please enter the date ........");
        LocalDate preferDate = null;
        try {
            String date = in.readLine();
            LocalDate now = LocalDate.now();
            preferDate = LocalDate.of(now.getYear(), now.getMonth(), Integer.valueOf(date));
        } catch (IOException e) {
            e.printStackTrace();
        }


        MovieShowTime movieShowTime = selectShowTime(in);
        SeatType seatType = getSeatType(in);


        ShowTime showTime = theater.getMovieShow(preferDate, movieShowTime);
        Seats seats = showTime.seatType(seatType);
        System.out.println(seats);

        List<String> availableSeats = seats.availableSeats();
        log.info("Available seats..........");
        availableSeats.forEach((seat) -> System.out.print(seat + " "));
        System.out.println();

        log.info("Please enter the seats :");
        String line = null;
        try {
            line = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> listOfSeat = Arrays.asList(line.split(" "));
        listOfSeat.forEach(System.out::println);

        seats.seatReserve(listOfSeat);

        PaymentService paymentService = new PaymentService(50000);

        int payment = getPayment(listOfSeat.size(), seatType);

        PaymentResponse paymentResponse = paymentService.payment(payment);

        if (paymentResponse.getPaymentStatus().equals(PaymentStatus.SUCCESS)) {
            log.info("Payment success");
            GenerateBookingId generateBookingId = GenerateBookingId.getInstance();
            String bookingId = generateBookingId.generateId(movieShowTime);
            BookSeat bookSeat = getBookSeat(listOfSeat, user, bookingId, movieShowTime, seatType, paymentResponse, preferDate);
            log.info("Book seat user {}", bookSeat.getUser().getUserName());

            Map<String, BookStatus> stringBookStatusMap = seats.seatBook(bookSeat);
            bookSeat.setBookStatusMap(stringBookStatusMap);
            showDetails(bookSeat);

        } else {
            log.info("Payment fail");
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            seats.seatRelease(listOfSeat);
        }


    }

    private int getPayment(int size, SeatType seatType) {
        int payment = 0;

        if (seatType.equals(SeatType.NORMAL))
            payment = size * 250;

        if(seatType.equals(SeatType.SILVER))
            payment = size * 500;

        if(seatType.equals(SeatType.GOLD))
            payment = size * 750;

        return payment;
    }

    private BookSeat getBookSeat(List<String> listOfSeat, User user, String bookingId,
                                 MovieShowTime movieShowTime, SeatType seatType, PaymentResponse paymentResponse, LocalDate preferDate) {
        BookSeat bookSeat = new BookSeat();
        bookSeat.setSeats(listOfSeat);
        bookSeat.setUser(user);
        bookSeat.setBookingId(bookingId);
        bookSeat.setMovieShowTime(movieShowTime);
        bookSeat.setPaymentStatus(paymentResponse.getPaymentStatus());
        bookSeat.setSeatType(seatType);
        bookSeat.setBookStatusMap(new HashMap<>());
        bookSeat.setShowDate(preferDate);
        return bookSeat;
    }

    private User getUser(BufferedReader in) {
        User user = new User();


        try {
            log.info("Please enter the name : ");
            user.setUserName(in.readLine());

            log.info("Please enter the mobile no : ");
            user.setMobileNo(in.readLine());

            log.info("Please enter the email id : ");
            user.setEmailId(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return user;
    }

    private SeatType getSeatType(BufferedReader in) {
        SeatType seatType = null;
        boolean flag = true;
        while (flag) {
            log.info("Please enter the book type........");
            log.info("1. Normal");
            log.info("2. Silver");
            log.info("3. Gold");
            String time = null;
            try {
                time = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            switch (time) {

                case "1":
                    seatType = SeatType.NORMAL;
                    flag = false;
                    break;
                case "2":
                    seatType = SeatType.SILVER;
                    flag = false;
                    break;
                case "3":
                    seatType = SeatType.GOLD;
                    flag = false;
                    break;
                default:
                    log.info("please select the right choice");

            }
        }
        return seatType;
    }

    private MovieShowTime selectShowTime(BufferedReader in) {

        log.info("Select the show time");
        MovieShowTime movieShowTime = null;
        boolean flag = true;
        while (flag) {
            log.info("Please select the show time : ");
            log.info("1. Morning");
            log.info("2. Matinee");
            log.info("3. Evening");
            log.info("4. Exit");
            String time = null;
            try {
                time = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            switch (time) {

                case "1":
                    LocalTime morningShow = LocalTime.of(10, 0);
                    if (isAllowedBooking(morningShow)) {
                        movieShowTime = MovieShowTime.MORNING;
                        flag = false;
                    } else {
                        log.info("Booking is not allowed , please select other time");
                    }
                    break;
                case "2":
                    LocalTime matineeShow = LocalTime.of(15, 0);
                    if (isAllowedBooking(matineeShow)) {
                        movieShowTime = MovieShowTime.MATINEE;
                        flag = false;
                    } else {
                        log.info("Booking is not allowed , please select other time");
                    }
                    break;
                case "3":
                    LocalTime eveningShow = LocalTime.of(18, 0);
                    if (isAllowedBooking(eveningShow)) {
                        movieShowTime = MovieShowTime.EVENING;
                        flag = false;
                    } else {
                        log.info("Booking is not allowed , please select other time");
                    }
                    break;

                case "4":
                    System.exit(0);
                default:
                    System.out.println("please select the right choice");

            }
        }

        return movieShowTime;

    }

    private boolean isAllowedBooking(LocalTime showTime) {
        LocalTime bookingTime = LocalTime.now();
        return bookingTime.plusMinutes(14).isBefore(showTime);
    }

    private void showDetails(BookSeat bookSeat) {
        log.info("Seat book successfully....................");
        log.info("Booking Id {}", bookSeat.getBookingId());
        log.info("Show date is {}", bookSeat.getShowDate());
        log.info("Show time {}", bookSeat.getMovieShowTime());
        log.info("Seat type {}", bookSeat.getSeatType());

        log.info("Seats no are .........");
        bookSeat.getSeats().forEach((seatNo) -> log.info(seatNo + " "));
    }
}
