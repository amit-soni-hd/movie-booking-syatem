package com.example.movie.theater;

import com.example.movie.theater.booking.TicketBooking;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class TheaterApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheaterApplication.class, args);
		TicketBooking ticketBooking = new TicketBooking();
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		executorService.submit(ticketBooking);
		executorService.shutdown();

		try {
			executorService.awaitTermination(3, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
