package shop.readmecorp.userserverreadme.modules.user.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import shop.readmecorp.userserverreadme.common.auth.session.MyUserDetails;
import shop.readmecorp.userserverreadme.common.exception.Exception400;
import shop.readmecorp.userserverreadme.common.dto.ResponseDTO;
import shop.readmecorp.userserverreadme.modules.file.entity.File;
import shop.readmecorp.userserverreadme.modules.file.entity.FileInfo;
import shop.readmecorp.userserverreadme.modules.file.enums.FileType;
import shop.readmecorp.userserverreadme.modules.file.service.FileInfoService;
import shop.readmecorp.userserverreadme.modules.file.service.FileService;
import shop.readmecorp.userserverreadme.modules.user.dto.UserDTO;
import shop.readmecorp.userserverreadme.modules.user.entity.User;
import shop.readmecorp.userserverreadme.modules.user.request.UserSaveRequest;
import shop.readmecorp.userserverreadme.modules.user.response.UserDetailResponse;
import shop.readmecorp.userserverreadme.modules.user.response.UserResponse;
import shop.readmecorp.userserverreadme.modules.user.service.UserService;

import javax.validation.Valid;

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
