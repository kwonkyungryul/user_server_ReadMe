package shop.readmecorp.userserverreadme.modules.user.controller;


import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import shop.readmecorp.userserverreadme.common.exception.Exception400;
import shop.readmecorp.userserverreadme.modules.book.dto.ResponseDTO;
import shop.readmecorp.userserverreadme.modules.file.entity.File;
import shop.readmecorp.userserverreadme.modules.file.entity.FileInfo;
import shop.readmecorp.userserverreadme.modules.file.enums.FileType;
import shop.readmecorp.userserverreadme.modules.file.service.FileInfoService;
import shop.readmecorp.userserverreadme.modules.file.service.FileService;
import shop.readmecorp.userserverreadme.modules.user.entity.User;
import shop.readmecorp.userserverreadme.modules.user.request.UserSaveRequest;
import shop.readmecorp.userserverreadme.modules.user.response.UserResponse;
import shop.readmecorp.userserverreadme.modules.user.service.UserService;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final FileInfoService fileInfoService;
    private final FileService fileService;

    public UserController(UserService userService, FileInfoService fileInfoService, FileService fileService) {
        this.userService = userService;
        this.fileInfoService = fileInfoService;
        this.fileService = fileService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> saveUser(
            @Valid @RequestBody UserSaveRequest request,
            Errors error) {
        if (error.hasErrors()) {
            throw new Exception400(error.getAllErrors().get(0).getDefaultMessage());
        }

        // fileInfo 생성
        FileInfo fileInfo = fileInfoService.save(FileType.USER);

        // file 생성 (파일이름, url 등 빈값)
        File file = fileService.save(fileInfo);

        // 계정 생성
        User save = userService.join(request, fileInfo);

        return ResponseEntity.ok(save.toResponse());
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
