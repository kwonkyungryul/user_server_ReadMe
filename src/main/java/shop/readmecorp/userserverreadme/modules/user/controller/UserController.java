package shop.readmecorp.userserverreadme.modules.user.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.readmecorp.userserverreadme.common.exception.Exception400;
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
}
