package vinelouzada.yfood.user;

import jakarta.persistence.*;
import vinelouzada.yfood.payment.PaymentType;

import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<PaymentType> paymentTypes;

    @Deprecated
    public User() {}

    public User(String email, PaymentType... paymentType) {
        this.email = email;
        this.paymentTypes = Set.of(paymentType);
    }
}
