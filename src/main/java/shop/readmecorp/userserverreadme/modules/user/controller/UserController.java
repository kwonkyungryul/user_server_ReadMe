package shop.readmecorp.userserverreadme.modules.user.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.readmecorp.userserverreadme.auth.session.MyUserDetails;
import shop.readmecorp.userserverreadme.modules.common.dto.ResponseDTO;
import shop.readmecorp.userserverreadme.modules.user.dto.UserDTO;
import shop.readmecorp.userserverreadme.modules.user.service.UserService;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/my")
    public ResponseEntity<?> getMyPage(@AuthenticationPrincipal MyUserDetails myUserDetails) {

        return ResponseEntity.ok(new ResponseDTO<>(1, "마이페이지 조회가 완료되었습니다.", userService.getMyPage(myUserDetails)));
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<UserDTO>> getUser(@AuthenticationPrincipal MyUserDetails myUserDetails) {

        return ResponseEntity.ok(new ResponseDTO<>(1, "유저 조회가 완료되었습니다.", userService.getUser(myUserDetails.getUser())));
    }
}
