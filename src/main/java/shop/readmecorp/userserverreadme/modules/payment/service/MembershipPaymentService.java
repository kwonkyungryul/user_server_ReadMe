package shop.readmecorp.userserverreadme.modules.payment.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.readmecorp.userserverreadme.modules.membership.entity.Membership;
import shop.readmecorp.userserverreadme.modules.membership.repository.MembershipRepository;
import shop.readmecorp.userserverreadme.modules.payment.dto.MembershipPaymentDTO;
import shop.readmecorp.userserverreadme.modules.payment.entity.MembershipPayment;
import shop.readmecorp.userserverreadme.modules.payment.enums.PaymentStatus;
import shop.readmecorp.userserverreadme.modules.payment.enums.PaymentType;
import shop.readmecorp.userserverreadme.modules.payment.repository.MembershipPaymentRepository;
import shop.readmecorp.userserverreadme.modules.payment.request.MembershipPaymentSaveRequest;
import shop.readmecorp.userserverreadme.modules.user.entity.User;
import shop.readmecorp.userserverreadme.util.DateTimeConverter;

import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class MembershipPaymentService {

    private final MembershipPaymentRepository membershipPaymentRepository;

    private final MembershipRepository membershipRepository;

    public MembershipPaymentService(MembershipPaymentRepository membershipPaymentRepository, MembershipRepository membershipRepository) {
        this.membershipPaymentRepository = membershipPaymentRepository;
        this.membershipRepository = membershipRepository;
    }

    public List<MembershipPaymentDTO> getMyList(User user) {
        return membershipPaymentRepository.findByStatusNotAndUser(PaymentStatus.DELETE, user)
                .stream()
                .map(MembershipPayment::toDTO)
                .collect(Collectors.toList());
    }


    public Optional<MembershipPaymentDTO> getMemberShipPayment(Integer id, User user) {
        return membershipPaymentRepository.findByStatusAndUserAndId(PaymentStatus.ACTIVE, user, id)
                .map(MembershipPayment::toDTO);
    }

    public Optional<MembershipPayment> getMyMemberShip(User user, Integer membershipId) {
        return membershipPaymentRepository.findByUserAndStatusNotAndId(user, PaymentStatus.DELETE, membershipId);
    }

    public void delete(MembershipPayment membershipPayment) {
        membershipPayment.setStatus(PaymentStatus.DELETE);
        membershipPaymentRepository.save(membershipPayment);
    }

    @Transactional
    public Integer save(User user, MembershipPaymentSaveRequest request, Membership membership) {

        Integer paymentNo = membershipRepository.countBy() + 1;
        membershipPaymentRepository.save(new MembershipPayment(
                null,
                paymentNo,
                user,
                membership,
                DateTimeConverter.stringToLocalDateTime(request.getMembershipStartTime()),
                DateTimeConverter.stringToLocalDateTime(request.getMembershipStartTime()).plus(Period.ofDays(Integer.parseInt(membership.getMembershipTerm()))),
                request.getPrice(),
                DateTimeConverter.stringToLocalDateTime(request.getPaymentTime()),
                PaymentType.MEMBERSHIP,
                PaymentStatus.ACTIVE
        ));
        return paymentNo;

    }
}
