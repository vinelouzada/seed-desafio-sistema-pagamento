package vinelouzada.yfood.restaurant;

import jakarta.persistence.*;
import vinelouzada.yfood.payment.PaymentType;

import java.util.Set;

@Entity
@Table(name = "restaurants")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "restaurant_payment_types",
            joinColumns = @JoinColumn(name = "restaurant_id")
    )
    @Column(name = "payment_type")
    private Set<PaymentType> paymentTypes;

    @Deprecated
    public Restaurant() {}

    public Restaurant(String name, Set<PaymentType> paymentTypes) {
        this.name = name;
        this.paymentTypes = paymentTypes;
    }

    public boolean acceptsPaymentType(PaymentType paymentType) {
        return paymentTypes.contains(paymentType);
    }
}
