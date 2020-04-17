package com.example.movie.theater.provider;

import com.example.movie.theater.show.MovieShowTime;

import java.time.LocalDate;

public class GenerateBookingId {

    private static int count = 1;
    private static GenerateBookingId generateBookingId = new GenerateBookingId();

    public static GenerateBookingId getInstance() {
        if(generateBookingId == null)
            generateBookingId = new GenerateBookingId();

        return generateBookingId;
    }



    public String generateId(MovieShowTime movieShowTime) {

        LocalDate localDate = LocalDate.now();
        String day = String.valueOf(localDate.getDayOfMonth());
        String month = String.valueOf(localDate.getMonth().getValue());
        String year = String.valueOf(localDate.getYear()).substring(2,4);

        if(day.length() == 1) {
            day = "0" + day;
        }
        if(month.length() == 1)
            month = "0"+month;

        String time = movieShowTime.toString().substring(0,3);
        String countId = count+"";

        if(countId.length() == 1)
            countId = "00"+countId;
        else if(countId.length() == 2)
            countId = "0"+countId;
        else
            countId = "0"+countId;

        String id = "RM"+day+month+year+time+countId;
        count++;
        return id;
    }
}
