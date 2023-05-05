package shop.readmecorp.userserverreadme.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shop.readmecorp.userserverreadme.common.dto.MetaDTO;
import shop.readmecorp.userserverreadme.common.dto.ResponseDTO;
import shop.readmecorp.userserverreadme.common.enums.MainTabType;
import shop.readmecorp.userserverreadme.common.enums.PaymentTabType;
import shop.readmecorp.userserverreadme.common.enums.StorageBoxType;
import shop.readmecorp.userserverreadme.common.metadata.MainTab;
import shop.readmecorp.userserverreadme.common.metadata.NoticeTypeWrapper;
import shop.readmecorp.userserverreadme.common.metadata.PaymentTab;
import shop.readmecorp.userserverreadme.common.metadata.StorageBoxTab;
import shop.readmecorp.userserverreadme.modules.category.response.CategoryDTO;
import shop.readmecorp.userserverreadme.modules.category.service.CategoryService;
import shop.readmecorp.userserverreadme.modules.notification.enums.NotificationType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
public class CommonController {

    private final CategoryService categoryService;

    public CommonController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    
    @GetMapping("/meta")
    public ResponseEntity<?> getMetaData() {

        CategoryDTO categories = categoryService.getCategories();

        List<StorageBoxTab> storageBoxTabList = List.of(
                new StorageBoxTab("최근 본", StorageBoxType.recent.name()),
                new StorageBoxTab("스크랩", StorageBoxType.scrap.name()),
                new StorageBoxTab("구매", StorageBoxType.purchase.name()),
                new StorageBoxTab("북마크", StorageBoxType.bookmark.name())
        );

        List<MainTab> mainTabList = List.of(
                new MainTab("전체", MainTabType.ALL.name()),
                new MainTab("베스트셀러", MainTabType.BESTSELLER.name()),
                new MainTab("추천", MainTabType.RECOMMEND.name()),
                new MainTab("신간", MainTabType.NEW.name())
        );

        List<PaymentTab> paymentTabList = List.of(
                new PaymentTab("멤버십", PaymentTabType.MEMBERSHIP.name()),
                new PaymentTab("도서 구매", PaymentTabType.PURCHASE.name())
        );

        List<NoticeTypeWrapper> noticeTypeWrapperList = List.of(
                new NoticeTypeWrapper(NotificationType.NOTICE.name()),
                new NoticeTypeWrapper(NotificationType.PAYMENT.name()),
                new NoticeTypeWrapper(NotificationType.ADMIN.name()),
                new NoticeTypeWrapper(NotificationType.ADVERTISEMENT.name())
        );


        MetaDTO metaDTO = MetaDTO.builder()
                .category(categories)
                .user(null)
                .storageBoxTabList(storageBoxTabList)
                .mainTab(mainTabList)
                .paymentTab(paymentTabList)
                .noticeTypeWrapper(noticeTypeWrapperList)
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
