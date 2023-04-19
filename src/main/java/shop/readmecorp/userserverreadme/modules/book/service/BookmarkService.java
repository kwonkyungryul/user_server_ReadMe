package shop.readmecorp.userserverreadme.modules.book.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;
import shop.readmecorp.userserverreadme.modules.book.entity.Bookmark;
import shop.readmecorp.userserverreadme.modules.book.repository.BookmarkRepository;
import shop.readmecorp.userserverreadme.modules.book.request.BookmarkSaveRequest;
import shop.readmecorp.userserverreadme.modules.book.request.BookmarkUpdateRequest;

import java.util.Optional;

@RestController
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    public BookmarkService(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    public Page<Bookmark> getPage(Pageable pageable) {
        return bookmarkRepository.findAll(pageable);
    }

    public Optional<Bookmark> getBookmark(Integer id) {
        return bookmarkRepository.findById(id);
    }

    public Bookmark save(BookmarkSaveRequest request) {

        return null;
    }

    public Bookmark update(BookmarkUpdateRequest request, Bookmark bookmark) {
        return null;
    }

    public void delete(Bookmark bookmark) {
    }

}
