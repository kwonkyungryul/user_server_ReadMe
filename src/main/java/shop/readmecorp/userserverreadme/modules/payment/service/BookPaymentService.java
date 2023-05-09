package shop.readmecorp.userserverreadme.modules.payment.service;

import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.readmecorp.userserverreadme.modules.book.entity.Book;
import shop.readmecorp.userserverreadme.modules.book.repository.BookRepository;
import shop.readmecorp.userserverreadme.modules.payment.dto.BookPaymentDTO;
import shop.readmecorp.userserverreadme.modules.payment.entity.BookPayment;
import shop.readmecorp.userserverreadme.modules.payment.enums.PaymentStatus;
import shop.readmecorp.userserverreadme.modules.payment.repository.BookPaymentRepository;
import shop.readmecorp.userserverreadme.modules.user.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class BookPaymentService {

    private final BookPaymentRepository bookPaymentRepository;

    private final BookRepository bookRepository;

    public BookPaymentService(BookPaymentRepository bookPaymentRepository, BookRepository bookRepository) {
        this.bookPaymentRepository = bookPaymentRepository;
        this.bookRepository = bookRepository;
    }

    public List<BookPaymentDTO> getMyList(User user) {
        return bookPaymentRepository.findByStatusNotAndUser(PaymentStatus.DELETE, user).stream()
                .map(BookPayment::toDTO).collect(Collectors.toList());
    }

    public List<BookPaymentDTO> getBookPayments(Integer paymentNo, User user) {
        return bookPaymentRepository.findByStatusNotAndUserAndPaymentNo(PaymentStatus.DELETE, user, paymentNo)
                .stream()
                .map(BookPayment::toDTO)
                .collect(Collectors.toList());
    }

    public Integer save(User user, List<Book> books) {
        Integer paymentNo = bookPaymentRepository.countBy() + 1;

        books.forEach(book -> {
            bookPaymentRepository.save(
                    new BookPayment(
                            null,
                            user,
                            book,
                            book.getPrice(),
                            null,
                            PaymentStatus.WAIT
                    )
            );
        });

        return paymentNo;
    }
}
