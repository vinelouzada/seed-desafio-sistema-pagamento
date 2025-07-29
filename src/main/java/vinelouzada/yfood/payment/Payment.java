package vinelouzada.yfood.payment;

import jakarta.persistence.*;
import vinelouzada.yfood.restaurant.Restaurant;
import vinelouzada.yfood.user.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurant restaurant;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "payment_transactions",
            joinColumns = @JoinColumn(name = "payment_id")
    )
    private Set<Transaction> transactions;

    private BigDecimal total;
    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Deprecated
    public Payment() {}

    public Payment(PaymentType paymentType, Set<Transaction> transactions, BigDecimal total, Restaurant restaurant, User user, LocalDateTime createdAt) {
        this.paymentType = paymentType;
        this.transactions = transactions;
        this.total = total;
        this.restaurant = restaurant;
        this.user = user;
        this.code = UUID.randomUUID().toString();
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public User getUser() {
        return user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void finish() {
        transactions.add(new Transaction(TransactionStatus.OK));
    }
}