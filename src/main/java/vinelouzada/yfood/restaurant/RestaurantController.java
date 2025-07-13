package vinelouzada.yfood.restaurant;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vinelouzada.yfood.restaurant.dto.CreateRestaurantRequest;

@RestController
public class RestaurantController {
    private final RestaurantRepository restaurantRepository;

    public RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @PostMapping("/restaurant/create")
    public void save(@RequestBody @Valid CreateRestaurantRequest request) {
        restaurantRepository.save(request.toRestaurant());
    }
}