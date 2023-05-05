package shop.readmecorp.userserverreadme.modules.book.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import shop.readmecorp.userserverreadme.modules.book.entity.Book;


public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query("SELECT b FROM Book b INNER JOIN Heart h ON b.id = h.book.id GROUP BY h.book.id ORDER BY COUNT(h) DESC")
    Page<Book> findByBookHeartCount(Pageable pageable);

    @Query("SELECT b FROM Book b INNER JOIN BookPayment bp ON b.id = bp.book.id GROUP BY bp.book.id ORDER BY COUNT(bp) DESC")
    Page<Book> findByBookPaymentDESC(Pageable pageable);

    Page<Book> findByBigCategoryId(Integer bigCategoryId, Pageable pageable);

    Page<Book> findByBigCategoryIdAndSmallCategoryId(Integer bigCategoryId, Integer smallCategoryId, Pageable pageable);
}
