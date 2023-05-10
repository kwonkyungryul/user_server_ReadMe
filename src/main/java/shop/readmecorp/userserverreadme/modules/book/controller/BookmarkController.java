package shop.readmecorp.userserverreadme.modules.book.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import shop.readmecorp.userserverreadme.auth.session.MyUserDetails;
import shop.readmecorp.userserverreadme.modules.book.dto.BookDTO;
import shop.readmecorp.userserverreadme.modules.common.dto.ResponseDTO;
import shop.readmecorp.userserverreadme.modules.common.exception.Exception400;
import shop.readmecorp.userserverreadme.modules.book.BookConst;
import shop.readmecorp.userserverreadme.modules.book.BookmarkConst;
import shop.readmecorp.userserverreadme.modules.book.dto.BookmarkDTO;
import shop.readmecorp.userserverreadme.modules.book.entity.Book;
import shop.readmecorp.userserverreadme.modules.book.entity.Bookmark;
import shop.readmecorp.userserverreadme.modules.book.request.BookmarkSaveRequest;
import shop.readmecorp.userserverreadme.modules.book.response.BookmarkResponse;
import shop.readmecorp.userserverreadme.modules.book.service.BookService;
import shop.readmecorp.userserverreadme.modules.book.service.BookmarkService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bookmarks")
public class  BookmarkController {

    private final BookmarkService bookmarkService;
    private final BookService bookService;

    public BookmarkController(BookmarkService bookmartService, BookService bookService) {
        this.bookmarkService = bookmartService;
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<BookDTO>>> getBooks (
            @AuthenticationPrincipal MyUserDetails myUserDetails
    ) {
        List<BookmarkDTO> bookmarkList = bookmarkService.getBookmarks(myUserDetails.getUser());
        return ResponseEntity.ok(
                new ResponseDTO<>(1, "조회 성공",
                    bookmarkList.stream()
                            .map(BookmarkDTO::getBook)
                            .distinct()
                            .collect(Collectors.toList())
                )
        );
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<ResponseDTO<List<BookmarkDTO>>> getBookmarks(
            @PathVariable Integer bookId,
            @AuthenticationPrincipal MyUserDetails myUserDetails
    ) {
        Optional<Book> bookOptional = bookService.getBook(bookId);
        if (bookOptional.isEmpty()) {
            throw new Exception400(BookConst.notFound);
        }

        var bookmarkList = bookmarkService.getBookmarks(bookOptional.get(), myUserDetails.getUser());
        return ResponseEntity.ok(new ResponseDTO<>(1, "조회 성공", bookmarkList));
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<BookmarkResponse>> saveBook(
            @Valid @RequestBody BookmarkSaveRequest request,
            Errors error,
            @AuthenticationPrincipal MyUserDetails myUserDetails
    ) {
        if (error.hasErrors()) {
            throw new Exception400(error.getAllErrors().get(0).getDefaultMessage());
        }

        Optional<Book> bookOptional = bookService.getBook(request.getBookId());
        if (bookOptional.isEmpty()) {
            throw new Exception400(BookConst.notFound);
        }

        Bookmark save = bookmarkService.save(request, bookOptional.get(), myUserDetails.getUser());
        return ResponseEntity.ok(new ResponseDTO<>(1, "북마크 저장을 성공했습니다.", save.toResponse()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<Void>> delete(
            @PathVariable Integer id,
            @AuthenticationPrincipal MyUserDetails myUserDetails
    ) {
        Optional<Bookmark> optionalBookmark = bookmarkService.getBookmark(id, myUserDetails.getUser());
        if (optionalBookmark.isEmpty()) {
            throw new Exception400(BookmarkConst.notFound);
        }

        bookmarkService.delete(optionalBookmark.get());
        return ResponseEntity.ok(new ResponseDTO<>(1, "북마크 삭제를 성공했습니다.", null));
    }
    
}
