package shop.readmecorp.userserverreadme.modules.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.readmecorp.userserverreadme.modules.payment.entity.MembershipPayment;
import shop.readmecorp.userserverreadme.modules.user.entity.User;

import java.util.Optional;

public interface MembershipPaymentRepository extends JpaRepository<MembershipPayment, Integer> {
    Optional<MembershipPayment> findByUserId(Integer user_id);
}
