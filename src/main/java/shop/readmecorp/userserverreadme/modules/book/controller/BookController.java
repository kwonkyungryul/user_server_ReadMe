package shop.readmecorp.userserverreadme.modules.book.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import shop.readmecorp.userserverreadme.common.auth.session.MyUserDetails;
import shop.readmecorp.userserverreadme.common.exception.Exception400;
import shop.readmecorp.userserverreadme.modules.banner.dto.BannerDTO;
import shop.readmecorp.userserverreadme.modules.banner.entity.Banner;
import shop.readmecorp.userserverreadme.modules.banner.service.BannerService;
import shop.readmecorp.userserverreadme.modules.book.BookConst;
import shop.readmecorp.userserverreadme.common.dto.ResponseDTO;
import shop.readmecorp.userserverreadme.modules.book.dto.BookDTO;
import shop.readmecorp.userserverreadme.modules.book.dto.BookMainDTO;
import shop.readmecorp.userserverreadme.modules.book.entity.Book;
import shop.readmecorp.userserverreadme.modules.book.response.BookDetailResponse;
import shop.readmecorp.userserverreadme.modules.book.response.BookResponse;
import shop.readmecorp.userserverreadme.modules.book.service.BookService;
import shop.readmecorp.userserverreadme.modules.file.dto.FileDTO;
import shop.readmecorp.userserverreadme.modules.file.service.FileService;
import shop.readmecorp.userserverreadme.modules.review.dto.ReviewNoneBookDTO;
import shop.readmecorp.userserverreadme.modules.review.enums.ReviewStatus;
import shop.readmecorp.userserverreadme.modules.review.service.ReviewService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final ReviewService reviewService;
    private final FileService fileService;
    private final BannerService bannerService;

    public BookController(BookService bookService, ReviewService reviewService, FileService fileService, BannerService bannerService) {
        this.bookService = bookService;
        this.reviewService = reviewService;
        this.fileService = fileService;
        this.bannerService = bannerService;
    }


    @GetMapping
    public ResponseEntity<?> getPage(Pageable pageable,
                                     @RequestParam(defaultValue = "0") Integer bigCategoryId,
                                     @RequestParam(defaultValue = "0") Integer smallCategoryId,
                                     @RequestParam(defaultValue = "") String status,
                                     @AuthenticationPrincipal MyUserDetails myUserDetails
    ) {
        return ResponseEntity.ok(new ResponseDTO<>(1, "리스트 조회 성공",
                bookService.getPage(bigCategoryId, smallCategoryId, pageable, status, myUserDetails)));
    }


    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBook(@PathVariable Integer id) {
        var optionalBook = bookService.getBook(id);
        if (optionalBook.isEmpty()) {
            throw new Exception400(BookConst.notFound);
        }
        return ResponseEntity.ok(optionalBook.get().toResponse());
    }

    @GetMapping("/{id}/detail")
    public ResponseEntity<?> getBookDetail(
            @PathVariable Integer id,
            @AuthenticationPrincipal MyUserDetails myUserDetails,
            Pageable pageable
    ) {
        Optional<Book> optionalBook = bookService.getBook(id);
        if (optionalBook.isEmpty()) {
            throw new Exception400(BookConst.notFound);
        }

        Book book = optionalBook.get();
        Page<ReviewNoneBookDTO> reviewDTOList = reviewService.getReviews(book.getId(), ReviewStatus.ACTIVE, pageable);
        if (reviewDTOList == null) {
            throw new Exception400(BookConst.notFound);
        }

        Optional<FileDTO> optionalEpubFile = fileService.getFile(book.getEpub().getId());
        if (optionalEpubFile.isEmpty()) {
            throw new Exception400("Epub 파일을 찾을 수 없습니다.");
        }

        Optional<FileDTO> optionalCoverFile = fileService.getFile(book.getCover().getId());
        if (optionalCoverFile.isEmpty()) {
            throw new Exception400("커버 파일을 찾을 수 없습니다.");
        }

        BookDetailResponse bookDetail = bookService.getBookDetail(myUserDetails == null ? null : myUserDetails.getUser(), book, optionalEpubFile.get(), optionalCoverFile.get(), reviewDTOList);
        return ResponseEntity.ok(new ResponseDTO<>(1, "도서 상세 조회 성공", bookDetail));
    }


}