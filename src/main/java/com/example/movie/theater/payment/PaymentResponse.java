package com.example.movie.theater.payment;

import com.example.movie.theater.payment.PaymentStatus;
import lombok.Data;

@Data
public class PaymentResponse {

    private PaymentStatus paymentStatus;
    private String message;
}
