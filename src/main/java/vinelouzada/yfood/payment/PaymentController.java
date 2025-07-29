package vinelouzada.yfood.payment;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vinelouzada.yfood.payment.dto.PaymentAvailable;
import vinelouzada.yfood.payment.integration.order.OrderClient;
import vinelouzada.yfood.payment.integration.order.OrderClientResponse;
import vinelouzada.yfood.payment.repository.PaymentRepository;
import vinelouzada.yfood.payment.validators.PaymentValidator;
import vinelouzada.yfood.restaurant.Restaurant;
import vinelouzada.yfood.restaurant.RestaurantRepository;
import vinelouzada.yfood.user.User;
import vinelouzada.yfood.user.UserRepository;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class PaymentController {

    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final Set<PaymentValidator> paymentValidators;
    private final OrderClient orderClient;
    private final PaymentRepository paymentRepository;

    public PaymentController(RestaurantRepository restaurantRepository, UserRepository userRepository, Set<PaymentValidator> paymentValidators, OrderClient orderClient, PaymentRepository paymentRepository) {
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
        this.paymentValidators = paymentValidators;
        this.orderClient = orderClient;
        this.paymentRepository = paymentRepository;
    }

    @PostMapping("/payment/offline")
    public ResponseEntity<Long> processPaymentOffline(@RequestBody @Valid PaymentRequest paymentRequest) {
        User user = userRepository.findById(paymentRequest.userId()).orElseThrow(EntityNotFoundException::new);
        Restaurant restaurant = restaurantRepository.findById(paymentRequest.restaurantId()).orElseThrow(EntityNotFoundException::new);

        OrderClientResponse orderClientResponse = orderClient.getOrderById(paymentRequest.orderId());

        if (!user.acceptsPaymentType(paymentRequest.paymentType()) && !restaurant.acceptsPaymentType(paymentRequest.paymentType())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Payment payment = paymentRepository.save(paymentRequest.toPaymentOffline(user, restaurant, orderClientResponse));

        return ResponseEntity.ok().body(payment.getId());
    }

    @GetMapping("/payment/user/{userId}/restaurant/{restaurantId}")
    public ResponseEntity<Set<PaymentAvailable>> paymentAvailableByUserAndRestaurant(@PathVariable Long userId, @PathVariable Long restaurantId) {
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(EntityNotFoundException::new);

        Set<PaymentAvailable> paymentsOptions = user.filterPossiblePaymentTypes(restaurant, paymentValidators)
                .stream()
                .map(PaymentAvailable::new)
                .collect(Collectors.toSet());

        return ResponseEntity.ok().body(paymentsOptions);
    }
}