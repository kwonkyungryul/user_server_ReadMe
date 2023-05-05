package shop.readmecorp.userserverreadme.modules.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.readmecorp.userserverreadme.common.exception.Exception400;
import shop.readmecorp.userserverreadme.modules.file.entity.FileInfo;
import shop.readmecorp.userserverreadme.modules.payment.dto.MembershipPaymentDTO;
import shop.readmecorp.userserverreadme.modules.payment.dto.MembershipPaymentNoneUserDTO;
import shop.readmecorp.userserverreadme.modules.payment.entity.MembershipPayment;
import shop.readmecorp.userserverreadme.modules.payment.repository.MembershipPaymentRepository;
import shop.readmecorp.userserverreadme.modules.user.UserConst;
import shop.readmecorp.userserverreadme.modules.user.dto.UserInfoDTO;
import shop.readmecorp.userserverreadme.modules.user.entity.User;
import shop.readmecorp.userserverreadme.modules.user.repository.UserRepository;
import shop.readmecorp.userserverreadme.modules.user.request.UserSaveRequest;
import shop.readmecorp.userserverreadme.modules.user.response.UserDetailResponse;
import shop.readmecorp.userserverreadme.modules.user.response.UserResponse;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final MembershipPaymentRepository membershipPaymentRepository;

    public UserService(UserRepository userRepository, MembershipPaymentRepository membershipPaymentRepository) {
        this.userRepository = userRepository;
        this.membershipPaymentRepository = membershipPaymentRepository;
    }

    @Transactional
    public User join(UserSaveRequest request, FileInfo fileInfo){

        User user = User.builder()
                .username(request.getUsername())
                .password(UUID.randomUUID().toString()) // 비밀번호 랜덤값 넣기
                .isMembership(false)
                .isAutoPayment(false)
                .fileInfo(fileInfo)
                .joinTime(LocalDateTime.now())
                .build();

        return userRepository.save(user);

    }

    public UserInfoDTO getUser(Integer userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new Exception400(UserConst.notFound);
        }
        User user = optionalUser.get();
        Optional<MembershipPayment> optionalMembershipPayment = membershipPaymentRepository.findByUserId(userId);

        MembershipPayment membershipPayment = optionalMembershipPayment.get();

        MembershipPaymentNoneUserDTO noneUserDTO = membershipPayment.toNoneUserDTO();

        return UserInfoDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .membershipPaymentNoneUserDTO(noneUserDTO)
                .build();

    }
}
