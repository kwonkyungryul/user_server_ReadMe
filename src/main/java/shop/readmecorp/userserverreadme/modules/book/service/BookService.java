package shop.readmecorp.userserverreadme.modules.book.service;

import org.springframework.stereotype.Service;
import shop.readmecorp.userserverreadme.modules.book.repository.BookRepository;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
}
