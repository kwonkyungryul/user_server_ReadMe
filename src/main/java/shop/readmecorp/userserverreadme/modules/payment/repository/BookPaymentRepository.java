package shop.readmecorp.userserverreadme.modules.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import shop.readmecorp.userserverreadme.modules.book.entity.Book;
import shop.readmecorp.userserverreadme.modules.payment.entity.BookPayment;
import shop.readmecorp.userserverreadme.modules.payment.enums.PaymentStatus;
import shop.readmecorp.userserverreadme.modules.user.entity.User;

import java.util.List;

public interface BookPaymentRepository extends JpaRepository<BookPayment, Integer> {
    List<BookPayment> findByStatusNotAndUser(PaymentStatus status, User user);
    List<BookPayment> findByStatusNotAndUserAndPaymentNo(PaymentStatus status, User user, Integer paymentNo);
    BookPayment findByStatusNotAndUserAndBook(PaymentStatus status, User user, Book book);
}
