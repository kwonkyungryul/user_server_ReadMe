package shop.readmecorp.userserverreadme.modules.bookmark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.readmecorp.userserverreadme.modules.bookmark.entity.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {
}
