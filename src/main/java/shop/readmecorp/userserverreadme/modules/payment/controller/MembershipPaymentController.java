package shop.readmecorp.userserverreadme.modules.payment.controller;

import org.springframework.web.bind.annotation.RestController;
import shop.readmecorp.userserverreadme.modules.payment.service.MembershipPaymentService;

@RestController
public class MembershipPaymentController {

    private final MembershipPaymentService membershipPaymentService;

    public MembershipPaymentController(MembershipPaymentService membershipPaymentService) {
        this.membershipPaymentService = membershipPaymentService;
    }
}
