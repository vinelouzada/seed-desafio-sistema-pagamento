package vinelouzada.yfood.user;

import jakarta.persistence.*;
import vinelouzada.yfood.payment.PaymentType;

import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name="user_payments_types",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "payment_type")
    private Set<PaymentType> paymentTypes;

    @Deprecated
    public User() {}

    public User(String email, Set<PaymentType> paymentTypes) {
        this.email = email;
        this.paymentTypes = paymentTypes;
    }

    public Set<PaymentType> getPaymentTypes() {
        return paymentTypes;
    }
}
