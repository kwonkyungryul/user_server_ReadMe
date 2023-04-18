package shop.readmecorp.userserverreadme.modules.book.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import shop.readmecorp.userserverreadme.modules.book.entity.Book;
import shop.readmecorp.userserverreadme.modules.book.repository.BookRepository;
import shop.readmecorp.userserverreadme.modules.book.request.BookSaveRequest;
import shop.readmecorp.userserverreadme.modules.book.request.BookUpdateRequest;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Page<Book> getPage(Pageable pageable) {
        return null;
    }

    public Optional<Book> getBook(Integer id) {
        return null;
    }

    public Book save(BookSaveRequest request) {

        return null;
    }

    public Book update(BookUpdateRequest request, Book book) {
        return null;
    }

    public void delete(Book book) {
    }
}
