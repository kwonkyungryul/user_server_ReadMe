package shop.readmecorp.userserverreadme.modules.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.readmecorp.userserverreadme.modules.file.entity.FileInfo;
import shop.readmecorp.userserverreadme.modules.user.entity.User;
import shop.readmecorp.userserverreadme.modules.user.repository.UserRepository;
import shop.readmecorp.userserverreadme.modules.user.request.UserSaveRequest;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
}
