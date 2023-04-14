package shop.readmecorp.userserverreadme.modules.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.readmecorp.userserverreadme.modules.book.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
