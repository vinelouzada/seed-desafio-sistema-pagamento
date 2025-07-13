package vinelouzada.yfood.payment.dto;

import vinelouzada.yfood.payment.PaymentType;

public record PaymentAvailable(
        String payment,
        String description
) {
    public PaymentAvailable(PaymentType paymentType){
        this(paymentType.name(), paymentType.getDescription());
    }
}