package shop.readmecorp.userserverreadme.modules.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import shop.readmecorp.userserverreadme.auth.jwt.MyJwtProvider;
import shop.readmecorp.userserverreadme.auth.session.MyUserDetails;
import shop.readmecorp.userserverreadme.modules.book.dto.BookDTO;
import shop.readmecorp.userserverreadme.modules.book.service.BookService;
import shop.readmecorp.userserverreadme.modules.common.request.FirebaseRequest;
import shop.readmecorp.userserverreadme.modules.common.dto.ResponseDTO;
import shop.readmecorp.userserverreadme.modules.common.exception.Exception400;
import shop.readmecorp.userserverreadme.modules.common.service.CommonService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
public class CommonController {

    private final CommonService commonService;
    private final BookService bookService;

    public CommonController(CommonService commonService, BookService bookService) {
        this.commonService = commonService;
        this.bookService = bookService;
    }

    // TODO 블로깅 - 처음엔 ~ 했는데 ~~이유 때문에 ~~ 하게 됐다
    @GetMapping("/meta")
    public ResponseEntity<?> getMetaData(@AuthenticationPrincipal MyUserDetails myUserDetails) {
        return ResponseEntity.ok(new ResponseDTO<>(1, "메타데이터 로드 성공", commonService.getMetaData(myUserDetails)));
    }

    @PostMapping("/login")
    public ResponseEntity<?> getUser(@Valid @RequestBody FirebaseRequest request, Errors error) {
        if (error.hasErrors()) {
            throw new Exception400(error.getAllErrors().get(0).getDefaultMessage());
        }

        HttpHeaders headers = new HttpHeaders();
        String jwt = commonService.getUser(request);
        headers.add(MyJwtProvider.HEADER, jwt);
        headers.add("Content-Type", "application/json;charset=utf-8");
        return new ResponseEntity<>(new ResponseDTO<>(1, "성공", jwt), headers, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(
            @RequestParam String keyword,
            @AuthenticationPrincipal MyUserDetails myUserDetails
    ) {
        return ResponseEntity.ok(new ResponseDTO<>(1, "성공", bookService.getSearch(keyword, myUserDetails)));
    }
}
