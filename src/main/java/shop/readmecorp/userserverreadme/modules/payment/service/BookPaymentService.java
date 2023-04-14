package shop.readmecorp.userserverreadme.modules.payment.service;

import org.springframework.stereotype.Service;
import shop.readmecorp.userserverreadme.modules.payment.repository.BookPaymentRepository;

@Service
public class BookPaymentService {

    private final BookPaymentRepository bookPaymentRepository;

    public BookPaymentService(BookPaymentRepository bookPaymentRepository) {
        this.bookPaymentRepository = bookPaymentRepository;
    }
}
