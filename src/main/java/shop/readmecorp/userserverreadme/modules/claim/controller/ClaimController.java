package shop.readmecorp.userserverreadme.modules.claim.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import shop.readmecorp.userserverreadme.common.auth.session.MyUserDetails;
import shop.readmecorp.userserverreadme.common.dto.ResponseDTO;
import shop.readmecorp.userserverreadme.common.exception.Exception400;
import shop.readmecorp.userserverreadme.modules.claim.dto.QuestionDTO;
import shop.readmecorp.userserverreadme.modules.claim.entity.Question;
import shop.readmecorp.userserverreadme.modules.claim.request.QuestionSaveRequest;
import shop.readmecorp.userserverreadme.modules.claim.service.QuestionService;
import shop.readmecorp.userserverreadme.modules.publisher.entity.Publisher;
import shop.readmecorp.userserverreadme.modules.publisher.service.PublisherService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/questions")
public class ClaimController {

    private final QuestionService questionService;
    private final PublisherService publisherService;


    public ClaimController(QuestionService questionService, PublisherService publisherService) {
        this.questionService = questionService;
        this.publisherService = publisherService;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<QuestionDTO>>> getList(
            @AuthenticationPrincipal MyUserDetails myUserDetails
    ) {
        return ResponseEntity.ok(new ResponseDTO<>(1, "문의하기 조회 완료되었습니다.", questionService.getList(myUserDetails.getUser())));
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<QuestionDTO>> save (
            @AuthenticationPrincipal MyUserDetails myUserDetails,
            @Valid @RequestBody QuestionSaveRequest request,
            Errors error
    )  {
        if (error.hasErrors()) {
            throw new Exception400(error.getAllErrors().get(0).getDefaultMessage());
        }

        Optional<Publisher> optionalPublisher = publisherService.getPublisher(request.getPublisherId());
        if (optionalPublisher.isEmpty()) {
            throw new Exception400("출판사 정보가 없습니다.");
        }

        return ResponseEntity.ok(new ResponseDTO<>(1, "문의가 완료되었습니다.", questionService.save(myUserDetails.getUser(), request, optionalPublisher.get())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<Void>> delete (
        @AuthenticationPrincipal MyUserDetails myUserDetails,
        @PathVariable Integer id
    )  {
        Optional<Question> questionOptional = questionService.getDetail(id);
        if (questionOptional.isEmpty()) {
            throw new Exception400("문의 글이 없습니다.");
        }

        Question question = questionOptional.get();
        if (question.getUser().getId().intValue() != myUserDetails.getUser().getId()) {
            throw new Exception400("자신의 문의만 삭제할 수 있습니다.");
        }
        questionService.delete(question);

        return ResponseEntity.ok(new ResponseDTO<>(1, "문의 삭제가 완료되었습니다.", null));
    }


}
