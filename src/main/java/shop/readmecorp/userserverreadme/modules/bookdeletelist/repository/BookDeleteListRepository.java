package shop.readmecorp.userserverreadme.modules.bookdeletelist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.readmecorp.userserverreadme.modules.bookdeletelist.entity.BookDeleteList;

public interface BookDeleteListRepository extends JpaRepository<BookDeleteList, Integer> {
}
