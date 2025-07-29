package vinelouzada.yfood.payment;

import jakarta.persistence.*;
import vinelouzada.yfood.restaurant.Restaurant;
import vinelouzada.yfood.user.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String externalOrderId;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurant restaurant;

    private BigDecimal total;
    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Deprecated
    public Payment() {}

    public Payment(PaymentType paymentType, PaymentStatus paymentStatus, BigDecimal total, Restaurant restaurant, User user, String externalOrderId, LocalDateTime createdAt) {
        this.paymentType = paymentType;
        this.paymentStatus = paymentStatus;
        this.total = total;
        this.restaurant = restaurant;
        this.user = user;
        this.externalOrderId = externalOrderId;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getExternalOrderId() {
        return externalOrderId;
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

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }
}