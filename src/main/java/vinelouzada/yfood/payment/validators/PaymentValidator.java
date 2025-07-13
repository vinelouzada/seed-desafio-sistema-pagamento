package vinelouzada.yfood.payment.validators;

import vinelouzada.yfood.payment.PaymentType;
import vinelouzada.yfood.user.User;

public interface PaymentValidator {
    boolean isValid(PaymentType paymentType, User user);
}