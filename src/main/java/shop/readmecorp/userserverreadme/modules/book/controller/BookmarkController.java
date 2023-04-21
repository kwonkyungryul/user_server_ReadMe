package shop.readmecorp.userserverreadme.modules.book.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import shop.readmecorp.userserverreadme.common.exception.Exception400;
import shop.readmecorp.userserverreadme.modules.book.BookmarkConst;
import shop.readmecorp.userserverreadme.modules.book.dto.BookmarkDTO;
import shop.readmecorp.userserverreadme.modules.book.entity.Bookmark;
import shop.readmecorp.userserverreadme.modules.book.request.BookmarkSaveRequest;
import shop.readmecorp.userserverreadme.modules.book.request.BookmarkUpdateRequest;
import shop.readmecorp.userserverreadme.modules.book.response.BookmarkResponse;
import shop.readmecorp.userserverreadme.modules.book.service.BookmarkService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bookmarks")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    public BookmarkController(BookmarkService bookmartService) {
        this.bookmarkService = bookmartService;
    }

    @GetMapping
    public ResponseEntity<Page<BookmarkDTO>> getPage(Pageable pageable) {
        Page<Bookmark> page = bookmarkService.getPage(pageable);
        List<BookmarkDTO> content = page.getContent()
                .stream()
                .map(Bookmark::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new PageImpl<>(content, pageable, page.getTotalElements()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookmarkResponse> getBookmark(@PathVariable Integer id) {
        var optionalBookmark = bookmarkService.getBookmark(id);
        if (optionalBookmark.isEmpty()) {
            throw new Exception400(BookmarkConst.notFound);
        }

        return ResponseEntity.ok(optionalBookmark.get().toResponse());
    }

    @PostMapping
    public ResponseEntity<BookmarkResponse> saveBook(
            @Valid @RequestBody BookmarkSaveRequest request,
            Errors error) {
        if (error.hasErrors()) {
            throw new Exception400(error.getAllErrors().get(0).getDefaultMessage());
        }

        Bookmark save = bookmarkService.save(request);

        return ResponseEntity.ok(save.toResponse());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookmarkResponse> updateBook(
            @PathVariable Integer id,
            @Valid @RequestBody BookmarkUpdateRequest request,
            Errors error
    ) {
        if (error.hasErrors()) {
            throw new Exception400(error.getAllErrors().get(0).getDefaultMessage());
        }

        Optional<Bookmark> optionalBookmark = bookmarkService.getBookmark(id);
        if (optionalBookmark.isEmpty()) {
            throw new Exception400(BookmarkConst.notFound);
        }

        Bookmark update = bookmarkService.update(request, optionalBookmark.get());
        return ResponseEntity.ok(update.toResponse());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        Optional<Bookmark> optionalBookmark = bookmarkService.getBookmark(id);
        if (optionalBookmark.isEmpty()) {
            throw new Exception400(BookmarkConst.notFound);
        }

        bookmarkService.delete(optionalBookmark.get());

        return ResponseEntity.ok("삭제가 완료되었습니다.");
    }
    
}
