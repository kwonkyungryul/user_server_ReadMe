package shop.readmecorp.userserverreadme.common.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shop.readmecorp.userserverreadme.common.auth.jwt.MyJwtProvider;
import shop.readmecorp.userserverreadme.common.auth.session.MyUserDetails;
import shop.readmecorp.userserverreadme.common.dto.CommonDTO;
import shop.readmecorp.userserverreadme.common.dto.FireBaseRequest;
import shop.readmecorp.userserverreadme.common.dto.MetaDTO;
import shop.readmecorp.userserverreadme.common.dto.ResponseDTO;
import shop.readmecorp.userserverreadme.common.enums.MainTabType;
import shop.readmecorp.userserverreadme.common.enums.PaymentTabType;
import shop.readmecorp.userserverreadme.common.enums.StorageBoxType;
import shop.readmecorp.userserverreadme.common.exception.Exception400;
import shop.readmecorp.userserverreadme.common.service.CommonService;
import shop.readmecorp.userserverreadme.modules.category.dto.BigCategoryDTO;
import shop.readmecorp.userserverreadme.modules.category.service.CategoryService;
import shop.readmecorp.userserverreadme.modules.notification.enums.NotificationType;
import shop.readmecorp.userserverreadme.modules.user.dto.UserInfoDTO;
import shop.readmecorp.userserverreadme.modules.user.service.UserService;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class CommonController {

    private final CommonService commonService;

    public CommonController(CommonService commonService) {
        this.commonService = commonService;
    }

    // TODO 블로깅 - 처음엔 ~ 했는데 ~~이유 때문에 ~~ 하게 됐다
    @GetMapping("/meta")
    public ResponseEntity<?> getMetaData(@AuthenticationPrincipal MyUserDetails myUserDetails) {
        return ResponseEntity.ok(new ResponseDTO<>(1, "메타데이터 로드 성공", commonService.getMetaData(myUserDetails)));
    }

    // TODO 시큐리티 설정 후 해야 함.
    @PostMapping("/login")
    public ResponseEntity<?> getUser(@Valid @RequestBody FireBaseRequest request, Errors error) {
        if (error.hasErrors()) {
            throw new Exception400(error.getAllErrors().get(0).getDefaultMessage());
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(MyJwtProvider.HEADER, commonService.getUser(request));
        headers.add("Content-Type", "application/json;charset=utf-8");
        return new ResponseEntity<>(new ResponseDTO<>(1, "성공", null), headers, HttpStatus.OK);
    }
}
