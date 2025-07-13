package vinelouzada.yfood.restaurant.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import vinelouzada.yfood.payment.PaymentType;
import vinelouzada.yfood.restaurant.Restaurant;

import java.util.Set;

public record CreateRestaurantRequest(
        @NotBlank String name,
        @NotNull Set<PaymentType> paymentTypes
) {
    public Restaurant toRestaurant() {
        return new Restaurant(name, paymentTypes);
    }
}