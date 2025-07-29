package vinelouzada.yfood.user;

import jakarta.persistence.*;
import vinelouzada.yfood.payment.PaymentType;
import vinelouzada.yfood.payment.validators.PaymentValidator;
import vinelouzada.yfood.restaurant.Restaurant;

import java.util.Set;
import java.util.stream.Collectors;

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

    public Set<PaymentType> filterPossiblePaymentTypes(Restaurant restaurant, Set<PaymentValidator>  paymentValidators) {
        return paymentTypes.stream()
                .filter(restaurant::acceptsPaymentType)
                .filter(type -> paymentValidators.stream().allMatch(paymentValidator -> paymentValidator.isValid(type, this)))
                .collect(Collectors.toSet());
    }

    public boolean acceptsPaymentType(PaymentType paymentType) {
        return paymentTypes.contains(paymentType);
    }

    public String getEmail() {
        return email;
    }
}
