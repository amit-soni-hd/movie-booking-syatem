package com.example.movie.theater.provider;

import com.example.movie.theater.show.MovieShowTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class GenerateBookingIdTest {


    @Before
    public void setUp() {
    }

    @Test
    public void generateTest() {
        GenerateBookingId generateBookingId = GenerateBookingId.getInstance();
        String id = generateBookingId.generateId(MovieShowTime.EVENING);
        assertEquals("EVE", id.substring(8, 11));
        assertEquals("1", id.substring(id.length()-1));

        String id1 = generateBookingId.generateId(MovieShowTime.MORNING);
        assertEquals("2", id1.substring(id1.length()-1));

    }

}