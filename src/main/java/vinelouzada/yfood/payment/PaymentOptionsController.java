package vinelouzada.yfood.payment;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import vinelouzada.yfood.payment.dto.PaymentAvailable;
import vinelouzada.yfood.restaurant.Restaurant;
import vinelouzada.yfood.restaurant.RestaurantRepository;
import vinelouzada.yfood.user.User;
import vinelouzada.yfood.user.UserRepository;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class PaymentOptionsController {

    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    public PaymentOptionsController(RestaurantRepository restaurantRepository, UserRepository userRepository) {

        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/payment-options/user/{userId}/restaurant/{restaurantId}")
    public ResponseEntity<Set<PaymentAvailable>> paymentAvailableByUserAndRestaurant(@PathVariable Long userId, @PathVariable Long restaurantId) {
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(EntityNotFoundException::new);

        Set<PaymentAvailable> paymentsOptions = user.getPaymentTypes()
                .stream()
                .filter(restaurant::acceptsPaymentType)
                .map(PaymentAvailable::new)
                .collect(Collectors.toSet());

        return ResponseEntity.ok().body(paymentsOptions);
    }
}