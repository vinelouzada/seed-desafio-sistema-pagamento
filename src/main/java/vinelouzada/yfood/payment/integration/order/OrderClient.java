package vinelouzada.yfood.payment.integration.order;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class OrderClient {

    private static final String ORDER_URL = "http://localhost:8080";

    public OrderClientResponse getOrderById(Long orderId) {

        WebClient webClient = WebClient.builder()
                .baseUrl(ORDER_URL)
                .build();

        Mono<OrderClientResponse> order = webClient.get()
                .uri("api/order/{orderId}", orderId)
                .retrieve()
                .bodyToMono(OrderClientResponse.class);

        return order.block();
    }
}