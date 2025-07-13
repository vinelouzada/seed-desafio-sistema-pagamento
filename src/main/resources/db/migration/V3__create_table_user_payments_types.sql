CREATE TABLE user_payments_types
(
    id           BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id      BIGINT NOT NULL,
    payment_type VARCHAR(255),

    CONSTRAINT fk_user_payments_types_user
        FOREIGN KEY (user_id)
            REFERENCES users (id)
)