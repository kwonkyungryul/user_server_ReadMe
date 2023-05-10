package shop.readmecorp.userserverreadme.modules.notification.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shop.readmecorp.userserverreadme.auth.session.MyUserDetails;
import shop.readmecorp.userserverreadme.modules.common.dto.ResponseDTO;
import shop.readmecorp.userserverreadme.modules.notification.dto.NotificationDTO;
import shop.readmecorp.userserverreadme.modules.notification.enums.OSType;
import shop.readmecorp.userserverreadme.modules.notification.service.NotificationService;

import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<NotificationDTO>>> getNotificationList(
        @AuthenticationPrincipal MyUserDetails myUserDetails,
        @RequestParam(defaultValue = "ANDROID") String osType
    ) {
        try {
            return ResponseEntity.ok(new ResponseDTO<>(1, "알림 조회 성공", notificationService.getNotificationList(myUserDetails, OSType.valueOf(osType))));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(-1, "잘못된 osType입니다.", null));
        }
    }

}
