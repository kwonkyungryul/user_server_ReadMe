package shop.readmecorp.userserverreadme.modules.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.readmecorp.userserverreadme.modules.payment.entity.MembershipPayment;

public interface MembershipPaymentRepository extends JpaRepository<MembershipPayment, Integer> {
}
