package vinelouzada.yfood.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import vinelouzada.yfood.payment.PaymentType;
import vinelouzada.yfood.user.User;

import java.util.Set;

public record CreateUserRequest(
        @Email String email,
        @NotNull Set<PaymentType> paymentTypes
) {
    public User toUser() {
        return new User(email, paymentTypes);
    }
}