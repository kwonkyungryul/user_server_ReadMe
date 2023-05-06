package shop.readmecorp.userserverreadme.common.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.usertype.UserType;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.readmecorp.userserverreadme.common.auth.jwt.MyJwtProvider;
import shop.readmecorp.userserverreadme.common.auth.session.MyUserDetails;
import shop.readmecorp.userserverreadme.common.dto.CommonDTO;
import shop.readmecorp.userserverreadme.common.dto.FireBaseRequest;
import shop.readmecorp.userserverreadme.common.dto.MetaDTO;
import shop.readmecorp.userserverreadme.common.enums.MainTabType;
import shop.readmecorp.userserverreadme.common.enums.PaymentTabType;
import shop.readmecorp.userserverreadme.common.enums.StorageBoxType;
import shop.readmecorp.userserverreadme.common.exception.Exception401;
import shop.readmecorp.userserverreadme.common.jpa.RoleType;
import shop.readmecorp.userserverreadme.modules.category.dto.BigCategoryDTO;
import shop.readmecorp.userserverreadme.modules.category.service.CategoryService;
import shop.readmecorp.userserverreadme.modules.notification.enums.NotificationType;
import shop.readmecorp.userserverreadme.modules.user.dto.UserInfoDTO;
import shop.readmecorp.userserverreadme.modules.user.entity.User;
import shop.readmecorp.userserverreadme.modules.user.repository.UserRepository;
import shop.readmecorp.userserverreadme.modules.user.service.UserService;

import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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

        UserInfoDTO userInfoDTO = null;
        if (myUserDetails != null) {
            userInfoDTO = userService.getUser(myUserDetails.getUser().getId());
        }

        List<CommonDTO> storageBoxTabList = List.of(
                new CommonDTO("최근 본", StorageBoxType.recent.name()),
                new CommonDTO("스크랩", StorageBoxType.scrap.name()),
                new CommonDTO("구매", StorageBoxType.purchase.name()),
                new CommonDTO("북마크", StorageBoxType.bookmark.name())
        );

        List<CommonDTO> mainTabList = List.of(
                new CommonDTO("전체", MainTabType.ALL.name()),
                new CommonDTO("베스트셀러", MainTabType.BESTSELLER.name()),
                new CommonDTO("추천", MainTabType.RECOMMEND.name()),
                new CommonDTO("신간", MainTabType.NEW.name())
        );

        List<CommonDTO> paymentTabList = List.of(
                new CommonDTO("멤버십", PaymentTabType.MEMBERSHIP.name()),
                new CommonDTO("도서 구매", PaymentTabType.PURCHASE.name())
        );

        List<String> notificationTypes = Arrays.stream(NotificationType.values()).map(Enum::name).collect(Collectors.toList());

        return MetaDTO.builder()
                .bigCategory(categories)
                .user(userInfoDTO)
                .storageBoxTabs(storageBoxTabList)
                .mainTabs(mainTabList)
                .paymentTabs(paymentTabList)
                .notificationTypes(notificationTypes)
                .build();
    }

    @Transactional
    public String getUser(FireBaseRequest request) {
        try {
            FirebaseAuth.getInstance().verifyIdToken(request.getIdToken());
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
        }

        Optional<User> optionalUser = userRepository.findByUsername(request.getCurrentUserEmail());
        User user = null;
        if (optionalUser.isEmpty()) {
            user = userRepository.save(new User(null, request.getCurrentUserEmail(), UUID.randomUUID().toString(), RoleType.USER.name(), false, false, LocalDateTime.now()));
        } else {
            user = optionalUser.get();
        }

//        User user = optionalUser.orElseGet(() -> userRepository.save(new User(null, request.getCurrentUserEmail(), UUID.randomUUID().toString(), RoleType.USER.name(), false, false, LocalDateTime.now())));

        return MyJwtProvider.create(user);
    }
}
