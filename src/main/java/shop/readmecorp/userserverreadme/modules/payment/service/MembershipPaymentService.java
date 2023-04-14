package shop.readmecorp.userserverreadme.modules.payment.service;

import org.springframework.stereotype.Service;
import shop.readmecorp.userserverreadme.modules.payment.repository.MembershipPaymentRepository;

@Service
public class MembershipPaymentService {

    private final MembershipPaymentRepository membershipPaymentRepository;

    public MembershipPaymentService(MembershipPaymentRepository membershipPaymentRepository) {
        this.membershipPaymentRepository = membershipPaymentRepository;
    }
}
