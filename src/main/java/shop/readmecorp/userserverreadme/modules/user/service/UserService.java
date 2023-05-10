package shop.readmecorp.userserverreadme.modules.user.service;

import org.springframework.stereotype.Service;
import shop.readmecorp.userserverreadme.auth.session.MyUserDetails;
import shop.readmecorp.userserverreadme.modules.common.exception.Exception400;
import shop.readmecorp.userserverreadme.modules.common.exception.Exception401;
import shop.readmecorp.userserverreadme.modules.payment.dto.MembershipPaymentNoneUserDTO;
import shop.readmecorp.userserverreadme.modules.payment.entity.MembershipPayment;
import shop.readmecorp.userserverreadme.modules.payment.enums.PaymentStatus;
import shop.readmecorp.userserverreadme.modules.payment.repository.MembershipPaymentRepository;
import shop.readmecorp.userserverreadme.modules.user.UserConst;
import shop.readmecorp.userserverreadme.modules.user.dto.UserDTO;
import shop.readmecorp.userserverreadme.modules.user.entity.User;
import shop.readmecorp.userserverreadme.modules.user.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final MembershipPaymentRepository membershipPaymentRepository;

    public UserService(UserRepository userRepository, MembershipPaymentRepository membershipPaymentRepository) {
        this.userRepository = userRepository;
        this.membershipPaymentRepository = membershipPaymentRepository;
    }

    public UserDTO getUser(User user) {
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if (optionalUser.isEmpty()) {
            throw new Exception400(UserConst.notFound);
        }
        User findUser = optionalUser.get();

        return UserDTO.builder()
                .id(findUser.getId())
                .username(findUser.getUsername())
                .role(findUser.getRole().name())
                .isMembership(findUser.getIsMembership())
                .isAutoPayment(findUser.getIsAutoPayment())
                .build();
    }

    public MembershipPaymentNoneUserDTO getMyPage(MyUserDetails myUserDetails) {
        if (myUserDetails == null) {
            throw new Exception401("인증이 되지 않았습니다.");
        }
        Optional<User> optionalUser = userRepository.findById(myUserDetails.getUser().getId());
        if (optionalUser.isEmpty()) {
            throw new Exception400(UserConst.notFound);
        }
        User user = optionalUser.get();
        Optional<MembershipPayment> optionalMembershipPayment = membershipPaymentRepository.findByUserIdAndStatusNot(user.getId(), PaymentStatus.DELETE);

        MembershipPaymentNoneUserDTO noneUserDTO = null;

        if (optionalMembershipPayment.isPresent()) {
            noneUserDTO = optionalMembershipPayment.get().toNoneUserDTO();
        }

        return noneUserDTO;
    }
}
