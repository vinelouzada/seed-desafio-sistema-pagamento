package vinelouzada.yfood.restaurant;

import jakarta.persistence.*;
import vinelouzada.yfood.payment.PaymentType;

import java.util.Set;

@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<PaymentType> paymentTypes;

    @Deprecated
    public Restaurant() {}

    public Restaurant(String name, PaymentType... paymentTypes) {
        this.name = name;
        this.paymentTypes = Set.of(paymentTypes);
    }
}
