package shop.readmecorp.userserverreadme.modules.book.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import shop.readmecorp.userserverreadme.common.exception.Exception400;
import shop.readmecorp.userserverreadme.modules.book.BookConst;
import shop.readmecorp.userserverreadme.common.dto.ResponseDTO;
import shop.readmecorp.userserverreadme.modules.book.entity.Book;
import shop.readmecorp.userserverreadme.modules.book.request.BookSaveRequest;
import shop.readmecorp.userserverreadme.modules.book.request.BookUpdateRequest;
import shop.readmecorp.userserverreadme.modules.book.response.BookResponse;
import shop.readmecorp.userserverreadme.modules.book.service.BookService;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

//    @GetMapping
//    public ResponseEntity<PageImpl<?>> getPage(Pageable pageable) {
//        return ResponseEntity.ok(bookService.getPage(pageable));
//    }

    @GetMapping
    public ResponseEntity<?> getPage(Pageable pageable,
                                     @RequestParam(defaultValue = "0") Integer bigCategoryId,
                                     @RequestParam(defaultValue = "0") Integer smallCategoryId) {
        return ResponseEntity.ok(new ResponseDTO<>(1, "전체/신간 리스트 조회 성공", bookService.getPage(bigCategoryId, smallCategoryId, pageable)));
    }

    @GetMapping("/recommends")
    public ResponseEntity<?> getRecommend(Pageable pageable) {
        return ResponseEntity.ok(new ResponseDTO<>(1, "추천 리스트 조회 성공", bookService.getRecommend(pageable)));
    }

    @GetMapping("/best-sellers")
    public ResponseEntity<?> getBestSeller(Pageable pageable) {
        return ResponseEntity.ok(new ResponseDTO<>(1, "베스트 셀러 리스트 조회 성공", bookService.getBestSellers(pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBook(@PathVariable Integer id) {
        var optionalBook = bookService.getBook(id);
        return ResponseEntity.ok(optionalBook.get().toResponse());
    }



    @GetMapping("/{id}/detail")
    public ResponseEntity<?> getBookDetail(@PathVariable Integer id) {
        return ResponseEntity.ok(new ResponseDTO<>(1, "도서 상세 조회 성공", bookService.getBookDetail(id)));
    }

    @GetMapping("/{id}/epub")
    public ResponseEntity<String> getEpubFile(@PathVariable Integer id) throws IOException {
        Optional<Book> optionalBook = bookService.getBook(id);
        Path path = Path.of(optionalBook.get().getFilePath());

        try (ZipInputStream zis = new ZipInputStream(Files.newInputStream(path))) {
            ZipEntry entry;
            int i = 0;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".html") || entry.getName().endsWith(".xhtml")) {
                    byte[] contentBytes = zis.readAllBytes();
                    return ResponseEntity.ok(new String(contentBytes, StandardCharsets.UTF_8));
                }
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<BookResponse> saveBook(
            @Valid @RequestBody BookSaveRequest request,
            Errors error) {
        if (error.hasErrors()) {
            throw new Exception400(error.getAllErrors().get(0).getDefaultMessage());
        }

        Book save = bookService.save(request);

        return ResponseEntity.ok(save.toResponse());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(
            @PathVariable Integer id,
            @Valid @RequestBody BookUpdateRequest request,
            Errors error
    ) {
        if (error.hasErrors()) {
            throw new Exception400(error.getAllErrors().get(0).getDefaultMessage());
        }

        Optional<Book> optionalBook = bookService.getBook(id);
        if (optionalBook.isEmpty()) {
            throw new Exception400(BookConst.notFound);
        }

        Book update = bookService.update(request, optionalBook.get());
        return ResponseEntity.ok(update.toResponse());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        Optional<Book> optionalBook = bookService.getBook(id);
        if (optionalBook.isEmpty()) {
            throw new Exception400(BookConst.notFound);
        }

        bookService.delete(optionalBook.get());

        return ResponseEntity.ok("삭제가 완료되었습니다.");
    }
}