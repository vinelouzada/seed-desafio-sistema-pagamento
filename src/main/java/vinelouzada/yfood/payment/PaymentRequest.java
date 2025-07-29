package vinelouzada.yfood.payment;

import jakarta.validation.constraints.NotNull;
import vinelouzada.yfood.payment.integration.order.OrderClientResponse;
import vinelouzada.yfood.restaurant.Restaurant;
import vinelouzada.yfood.user.User;

import java.time.LocalDateTime;
import java.util.Set;

public record PaymentRequest(
        @NotNull
        PaymentType paymentType,
        @NotNull
        Long restaurantId,
        @NotNull
        Long orderId,
        @NotNull
        Long userId
) {
    public Payment toPaymentOffline(User user, Restaurant restaurant, OrderClientResponse order) {
        if (!paymentType.isModePayOffline())
            throw new IllegalStateException("Payment type must be offline");

        return new Payment(paymentType, Set.of(new Transaction(TransactionStatus.WAITING)), order.total(), restaurant, user, order.orderId(), LocalDateTime.now());
    }
}