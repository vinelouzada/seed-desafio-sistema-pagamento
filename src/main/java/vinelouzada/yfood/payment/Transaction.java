package vinelouzada.yfood.payment;

import jakarta.persistence.*;

import java.util.UUID;

@Embeddable
public class Transaction {
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @Column(unique = true)
    private String code;

    @Deprecated
    public Transaction() {}

    public Transaction(TransactionStatus status) {
        this.status = status;
        this.code = UUID.randomUUID().toString();
    }

    public TransactionStatus  getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }
}