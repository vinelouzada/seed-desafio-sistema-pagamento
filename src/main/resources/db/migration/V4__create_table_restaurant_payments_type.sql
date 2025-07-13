CREATE TABLE restaurant_payment_types
(
    id           BIGINT PRIMARY KEY AUTO_INCREMENT,
    restaurant_id      BIGINT NOT NULL,
    payment_type VARCHAR(255),

    CONSTRAINT fk_restaurant_payment_types_user
        FOREIGN KEY (restaurant_id)
            REFERENCES restaurants (id)
)