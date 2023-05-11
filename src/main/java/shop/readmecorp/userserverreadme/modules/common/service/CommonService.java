package shop.readmecorp.userserverreadme.modules.common.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.readmecorp.userserverreadme.auth.jwt.MyJwtProvider;
import shop.readmecorp.userserverreadme.auth.session.MyUserDetails;
import shop.readmecorp.userserverreadme.modules.common.dto.CommonDTO;
import shop.readmecorp.userserverreadme.modules.common.request.FirebaseRequest;
import shop.readmecorp.userserverreadme.modules.common.dto.MetaDTO;
import shop.readmecorp.userserverreadme.modules.common.enums.MainTabType;
import shop.readmecorp.userserverreadme.modules.common.enums.PaymentTabType;
import shop.readmecorp.userserverreadme.modules.common.enums.StorageBoxType;
import shop.readmecorp.userserverreadme.modules.common.exception.Exception400;
import shop.readmecorp.userserverreadme.modules.common.jpa.RoleType;
import shop.readmecorp.userserverreadme.modules.category.dto.BigCategoryDTO;
import shop.readmecorp.userserverreadme.modules.category.service.CategoryService;
import shop.readmecorp.userserverreadme.modules.notification.enums.NotificationType;
import shop.readmecorp.userserverreadme.modules.user.dto.UserDTO;
import shop.readmecorp.userserverreadme.modules.user.entity.User;
import shop.readmecorp.userserverreadme.modules.user.repository.UserRepository;
import shop.readmecorp.userserverreadme.modules.user.service.UserService;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
public class CommonService {

    private final CategoryService categoryService;

    private final UserService userService;

    private final UserRepository userRepository;


    public CommonService(CategoryService categoryService, UserService userService, UserRepository userRepository) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public MetaDTO getMetaData(MyUserDetails myUserDetails) {
        List<BigCategoryDTO> categories = categoryService.getCategories();
        UserDTO userDTO = null;
        String jwt = "";
        if (myUserDetails != null) {
            // TODO 수정 필요
            userDTO = userService.getUser(myUserDetails.getUser());
            jwt = MyJwtProvider.create(myUserDetails.getUser());
        }

        List<CommonDTO> storageBoxTabList = Arrays.stream(StorageBoxType.values())
                .map(storageBoxType -> new CommonDTO(storageBoxType.getName(), storageBoxType.getRequestName()))
                .collect(Collectors.toList());

        List<CommonDTO> mainTabList = Arrays.stream(MainTabType.values())
                .map(mainTabType -> new CommonDTO(mainTabType.getName(), mainTabType.getRequestName()))
                .collect(Collectors.toList());

        List<CommonDTO> paymentTabList = Arrays.stream(PaymentTabType.values())
                .map(paymentTabType -> new CommonDTO(paymentTabType.getName(), paymentTabType.getRequestName()))
                .collect(Collectors.toList());

        List<String> notificationTypes = Arrays.stream(NotificationType.values()).map(Enum::name).collect(Collectors.toList());

        return MetaDTO.builder()
                .bigCategory(categories)
                .user(userDTO)
                .jwt(jwt)
                .storageBoxTabs(storageBoxTabList)
                .mainTabs(mainTabList)
                .paymentTabs(paymentTabList)
                .notificationTypes(notificationTypes)
                .build();
    }

    @Transactional
    public String getUser(FirebaseRequest request) {
        FirebaseToken firebaseToken = null;
        try {
            firebaseToken = FirebaseAuth.getInstance().verifyIdToken(request.getIdToken());
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
            throw new Exception400("잘못된 FirebaseToken 입니다.");
        }

        System.out.println(firebaseToken.getEmail());

        User user = null;
//         user 1번 테스트
        Optional<User> optionalUser = userRepository.findByUsername(firebaseToken.getEmail());
//        Optional<User> optionalUser = userRepository.findByUsername("kkr0787@nate.com");
        if (optionalUser.isEmpty()) {
            user = userRepository.save(new User(null, firebaseToken.getEmail(), UUID.randomUUID().toString(), RoleType.USER.name(), false, false));
        } else {
            user = optionalUser.get();
        }

        // TODO: FCM Token, OS Type 받아서 UserFcm 테이블에 저장해야함
        // Logout 시에는 UserFcm 테이블에서 삭제해야함 - EndPoint 추가

//        User user = optionalUser.orElseGet(() -> userRepository.save(new User(null, request.getCurrentUserEmail(), UUID.randomUUID().toString(), RoleType.USER.name(), false, false, LocalDateTime.now())));

        return MyJwtProvider.create(user);
    }
}
