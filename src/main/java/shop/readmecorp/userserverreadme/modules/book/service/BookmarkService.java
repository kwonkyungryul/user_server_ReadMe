package shop.readmecorp.userserverreadme.modules.book.service;

import org.springframework.web.bind.annotation.RestController;
import shop.readmecorp.userserverreadme.modules.book.repository.BookmarkRepository;

@RestController
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;

    public BookmarkService(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }
}
