ALTER TABLE payments
    DROP COLUMN payment_status;

ALTER TABLE payments
    ADD COLUMN payment_type VARCHAR(50);