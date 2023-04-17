package shop.readmecorp.userserverreadme.modules.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.readmecorp.userserverreadme.modules.book.entity.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {
}
