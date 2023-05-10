package shop.readmecorp.userserverreadme.modules.book.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import shop.readmecorp.userserverreadme.modules.book.dto.BookmarkDTO;
import shop.readmecorp.userserverreadme.modules.book.entity.Book;
import shop.readmecorp.userserverreadme.modules.book.entity.Bookmark;
import shop.readmecorp.userserverreadme.modules.book.enums.BookmarkStatus;
import shop.readmecorp.userserverreadme.modules.book.repository.BookmarkRepository;
import shop.readmecorp.userserverreadme.modules.book.request.BookmarkSaveRequest;
import shop.readmecorp.userserverreadme.modules.user.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Transactional(readOnly = true)
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    public BookmarkService(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    public List<BookmarkDTO> getBookmarks(Book book, User user) {
        return bookmarkRepository.findByBookAndStatusNotAndUser(book, BookmarkStatus.DELETE, user)
                .stream()
                .map(Bookmark::toDTO).collect(Collectors.toList());
    }

    public List<BookmarkDTO> getBookmarks(User user) {
        return bookmarkRepository.findByStatusNotAndUser(BookmarkStatus.DELETE, user)
                .stream()
                .map(Bookmark::toDTO).collect(Collectors.toList());
    }

    public Optional<Bookmark> getBookmark(Integer id, User user) {
        return bookmarkRepository.findByStatusNotAndIdAndUser(BookmarkStatus.DELETE, id, user);
    }

    @Transactional
    public Bookmark save(BookmarkSaveRequest request, Book book, User user) {
        return bookmarkRepository.save(
                new Bookmark (
                        null,
                        user,
                        book,
                        request.getPageNum(),
                        BookmarkStatus.ACTIVE
                )
        );
    }

    @Transactional
    public void delete(Bookmark bookmark) {
        bookmarkRepository.delete(bookmark);
    }


}
