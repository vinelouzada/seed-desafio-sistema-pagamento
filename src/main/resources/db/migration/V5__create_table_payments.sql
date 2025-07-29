CREATE TABLE payments
(
    id                BIGINT PRIMARY KEY AUTO_INCREMENT,
    external_order_id VARCHAR(255),
    user_id           BIGINT,
    restaurant_id     BIGINT,
    total             DECIMAL(19, 2),
    created_at        DATETIME,
    payment_type      VARCHAR(50),
    payment_status    VARCHAR(50),

    CONSTRAINT fk_payments_user FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_payments_restaurant FOREIGN KEY (restaurant_id) REFERENCES restaurants (id)
);