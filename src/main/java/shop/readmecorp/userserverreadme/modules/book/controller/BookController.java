package shop.readmecorp.userserverreadme.modules.book.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import shop.readmecorp.userserverreadme.common.exception.Exception400;
import shop.readmecorp.userserverreadme.modules.book.BookConst;
import shop.readmecorp.userserverreadme.modules.book.dto.BookDTO;
import shop.readmecorp.userserverreadme.modules.book.entity.Book;
import shop.readmecorp.userserverreadme.modules.book.request.BookSaveRequest;
import shop.readmecorp.userserverreadme.modules.book.request.BookUpdateRequest;
import shop.readmecorp.userserverreadme.modules.book.response.BookResponse;
import shop.readmecorp.userserverreadme.modules.book.service.BookService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<Page<BookDTO>> getPage(Pageable pageable) {
        Page<Book> page = bookService.getPage(pageable);
        List<BookDTO> content = page.getContent()
                .stream()
                .map(Book::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new PageImpl<>(content, pageable, page.getTotalElements()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBook(@PathVariable Integer id) {
        var optionalBook = bookService.getBook(id);
        if (optionalBook.isEmpty()) {
            throw new Exception400(BookConst.notFound);
        }

        return ResponseEntity.ok(optionalBook.get().toResponse());
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
