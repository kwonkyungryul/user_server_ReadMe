package shop.readmecorp.userserverreadme.modules.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.readmecorp.userserverreadme.modules.payment.entity.MembershipPayment;
import shop.readmecorp.userserverreadme.modules.payment.enums.PaymentStatus;
import shop.readmecorp.userserverreadme.modules.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface MembershipPaymentRepository extends JpaRepository<MembershipPayment, Integer> {
    Optional<MembershipPayment> findByUserIdAndStatusNot(Integer user_id, PaymentStatus status);
    Optional<MembershipPayment> findByUserAndStatusNotAndId(User user, PaymentStatus status, Integer id);

    List<MembershipPayment> findByStatusNotAndUser(PaymentStatus status, User user);
    Optional<MembershipPayment> findByStatusAndUserAndId(PaymentStatus status, User user, Integer id);
}
