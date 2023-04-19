package shop.readmecorp.userserverreadme.modules.book.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import shop.readmecorp.userserverreadme.common.exception.Exception400;
import shop.readmecorp.userserverreadme.modules.book.HeartConst;
import shop.readmecorp.userserverreadme.modules.book.dto.HeartDTO;
import shop.readmecorp.userserverreadme.modules.book.entity.Heart;
import shop.readmecorp.userserverreadme.modules.book.request.HeartSaveRequest;
import shop.readmecorp.userserverreadme.modules.book.request.HeartUpdateRequest;
import shop.readmecorp.userserverreadme.modules.book.response.HeartResponse;
import shop.readmecorp.userserverreadme.modules.book.service.HeartService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/hearts")
public class HeartController {

    private HeartService heartService;

    public HeartController(HeartService heartService) {
        this.heartService = heartService;
    }

    @GetMapping
    public ResponseEntity<Page<HeartDTO>> getPage(Pageable pageable) {
        Page<Heart> page = heartService.getPage(pageable);
        List<HeartDTO> content = page.getContent()
                .stream()
                .map(Heart::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new PageImpl<>(content, pageable, page.getTotalElements()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HeartResponse> getHeart(@PathVariable Integer id) {
        var optionalHeart = heartService.getHeart(id);
        if (optionalHeart.isEmpty()) {
            throw new Exception400(HeartConst.notFound);
        }

        return ResponseEntity.ok(optionalHeart.get().toResponse());
    }

    @PostMapping
    public ResponseEntity<HeartResponse> saveBook(
            @Valid @RequestBody HeartSaveRequest request,
            Errors error) {
        if (error.hasErrors()) {
            throw new Exception400(error.getAllErrors().get(0).getDefaultMessage());
        }

        Heart save = heartService.save(request);

        return ResponseEntity.ok(save.toResponse());
    }

    @PutMapping("/{id}")
    public ResponseEntity<HeartResponse> updateBook(
            @PathVariable Integer id,
            @Valid @RequestBody HeartUpdateRequest request,
            Errors error
    ) {
        if (error.hasErrors()) {
            throw new Exception400(error.getAllErrors().get(0).getDefaultMessage());
        }

        Optional<Heart> optionalHeart = heartService.getHeart(id);
        if (optionalHeart.isEmpty()) {
            throw new Exception400(HeartConst.notFound);
        }

        Heart update = heartService.update(request, optionalHeart.get());
        return ResponseEntity.ok(update.toResponse());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        Optional<Heart> optionalHeart = heartService.getHeart(id);
        if (optionalHeart.isEmpty()) {
            throw new Exception400(HeartConst.notFound);
        }

        heartService.delete(optionalHeart.get());

        return ResponseEntity.ok("삭제가 완료되었습니다.");
    }
}
