package com.example.movie.theater.payment;

public class PaymentService {

    private int balance;

    public PaymentService(int balance) {
        this.balance = balance;
    }


    public PaymentResponse payment(int rup) {
        PaymentResponse paymentResponse = new PaymentResponse();
        if(rup <= balance) {
            balance -=rup;
            paymentResponse.setPaymentStatus(PaymentStatus.SUCCESS);
            paymentResponse.setMessage("Payment successful");
        } else {
            paymentResponse.setMessage("Payment Fail");
            paymentResponse.setPaymentStatus(PaymentStatus.FAIL);
        }
        return paymentResponse;
    }

}
