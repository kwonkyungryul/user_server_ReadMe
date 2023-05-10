package shop.readmecorp.userserverreadme.modules.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.readmecorp.userserverreadme.modules.book.entity.Book;
import shop.readmecorp.userserverreadme.modules.book.entity.Bookmark;
import shop.readmecorp.userserverreadme.modules.book.enums.BookmarkStatus;
import shop.readmecorp.userserverreadme.modules.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {
    Optional<Bookmark> findByStatusNotAndIdAndUser(BookmarkStatus status, Integer id, User user);

    List<Bookmark> findByBookAndStatusNotAndUser(Book book, BookmarkStatus status, User user);
    List<Bookmark> findByStatusNotAndUser(BookmarkStatus status, User user);
}
