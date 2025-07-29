package vinelouzada.yfood;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Random;

@RestController
@RequestMapping("/api/order")
public class RandomOrderController {

    private final Random random = new Random();

    @GetMapping("/{id}")
    public ResponseEntity<OrderRandomResponse> getOrder(@PathVariable Long id) {
        int randomNumber = random.nextInt(10) + 1;

        if (randomNumber > 5) throw new EntityNotFoundException();

        return ResponseEntity.ok().body(new OrderRandomResponse(new BigDecimal(randomNumber), id));
    }

}

record OrderRandomResponse(BigDecimal total, Long id) {

}