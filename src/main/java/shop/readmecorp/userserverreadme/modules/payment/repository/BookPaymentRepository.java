package shop.readmecorp.userserverreadme.modules.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.readmecorp.userserverreadme.modules.payment.entity.BookPayment;

public interface BookPaymentRepository extends JpaRepository<BookPayment, Integer> {
}
