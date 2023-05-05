package shop.readmecorp.userserverreadme.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shop.readmecorp.userserverreadme.common.auth.session.MyUserDetails;
import shop.readmecorp.userserverreadme.common.dto.CommonDTO;
import shop.readmecorp.userserverreadme.common.dto.MetaDTO;
import shop.readmecorp.userserverreadme.common.dto.ResponseDTO;
import shop.readmecorp.userserverreadme.common.enums.MainTabType;
import shop.readmecorp.userserverreadme.common.enums.PaymentTabType;
import shop.readmecorp.userserverreadme.common.enums.StorageBoxType;
import shop.readmecorp.userserverreadme.modules.category.dto.BigCategoryDTO;
import shop.readmecorp.userserverreadme.modules.category.service.CategoryService;
import shop.readmecorp.userserverreadme.modules.notification.enums.NotificationType;
import shop.readmecorp.userserverreadme.modules.user.dto.UserInfoDTO;
import shop.readmecorp.userserverreadme.modules.user.service.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class CommonController {

    private final CategoryService categoryService;

    private final UserService userService;

    public CommonController(CategoryService categoryService, UserService userService) {
        this.categoryService = categoryService;
        this.userService = userService;
    }

    // TODO 블로깅 - 처음엔 ~ 했는데 ~~이유 때문에 ~~ 하게 됐다
    @GetMapping("/meta")
    public ResponseEntity<?> getMetaData(@AuthenticationPrincipal MyUserDetails myUserDetails) {
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

        MetaDTO metaDTO = MetaDTO.builder()
                .bigCategory(categories)
                .user(userInfoDTO)
                .storageBoxTabs(storageBoxTabList)
                .mainTabs(mainTabList)
                .paymentTabs(paymentTabList)
                .notificationTypes(notificationTypes)
                .build();

        return ResponseEntity.ok(new ResponseDTO<>(1, "메타데이터 로드 성공", metaDTO));
    }

    // TODO 시큐리티 설정 후 해야 함.
    @PostMapping("/login")
    public ResponseEntity<?> getUser(@RequestBody String idToken) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + idToken);

        log.debug("idToken : " + idToken);
        return new ResponseEntity<>(new ResponseDTO<>(1, "성공", null), headers, HttpStatus.OK);
    }
}
