CREATE TABLE payment_transactions
(
    payment_id BIGINT NOT NULL,
    status     VARCHAR(255),
    code       VARCHAR(255),
    PRIMARY KEY (code),
    FOREIGN KEY (payment_id) REFERENCES payments (id)
);