package vinelouzada.yfood.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vinelouzada.yfood.payment.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}