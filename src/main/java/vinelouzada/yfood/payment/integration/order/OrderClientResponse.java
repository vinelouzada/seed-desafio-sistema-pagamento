package vinelouzada.yfood.payment.integration.order;

import java.math.BigDecimal;

public record OrderClientResponse(
        String orderId,
        BigDecimal total
) {
}