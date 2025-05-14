CREATE TABLE restaurant_payments_type
(
    id           BIGINT PRIMARY KEY AUTO_INCREMENT,
    restaurant_id      BIGINT NOT NULL,
    payment_type VARCHAR(255),

    CONSTRAINT fk_restaurant_payments_type_user
        FOREIGN KEY (restaurant_id)
            REFERENCES users (id)
)