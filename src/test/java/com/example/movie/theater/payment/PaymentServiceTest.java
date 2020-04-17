package com.example.movie.theater.payment;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceTest {

    private PaymentService paymentService;

    @Before
    public void setUp() {
        this.paymentService = new PaymentService(1000);
    }

    @Test
    public void paymentTest() {
        PaymentResponse payment = paymentService.payment(100);
        Assert.assertEquals(PaymentStatus.SUCCESS, payment.getPaymentStatus());
    }

    @Test
    public void paymentTest_1() {
        PaymentResponse payment = paymentService.payment(1100);
        Assert.assertEquals(PaymentStatus.FAIL, payment.getPaymentStatus());
    }
}
