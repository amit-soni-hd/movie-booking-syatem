package com.example.movie.theater.seat;

import com.example.movie.theater.show.EveningShow;
import com.example.movie.theater.show.MatineeShow;
import com.example.movie.theater.show.MorningShow;

public class CreateShows {

    private MorningShow morningShow;
    private EveningShow eveningShow;
    private MatineeShow matineeShow;

    public CreateShows() {
        createMorningShow();
        createEveningShow();
        createMatineeShow();
    }

    private void createMorningShow() {
        NormalSeats normalSeats = new NormalSeats(100);
        SilverSeats silverSeats = new SilverSeats(75);
        GoldSeats goldSeats = new GoldSeats(50);
        this.morningShow = new MorningShow(normalSeats, silverSeats, goldSeats);
    }

    private void createEveningShow() {
        NormalSeats normalSeats = new NormalSeats(100);
        SilverSeats silverSeats = new SilverSeats(75);
        GoldSeats goldSeats = new GoldSeats(50);
        this.eveningShow = new EveningShow(normalSeats, silverSeats, goldSeats);
    }

    private void createMatineeShow() {
        NormalSeats normalSeats = new NormalSeats(100);
        SilverSeats silverSeats = new SilverSeats(75);
        GoldSeats goldSeats = new GoldSeats(50);
        this.matineeShow = new MatineeShow(normalSeats, silverSeats, goldSeats);
    }

    public MorningShow getMorningShow() {
        return morningShow;
    }

    public EveningShow getEveningShow() {
        return eveningShow;
    }

    public MatineeShow getMatineeShow() {
        return matineeShow;
    }
}
