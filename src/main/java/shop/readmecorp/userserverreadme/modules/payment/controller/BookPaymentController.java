package shop.readmecorp.userserverreadme.modules.payment.controller;

import org.springframework.web.bind.annotation.RestController;
import shop.readmecorp.userserverreadme.modules.payment.service.BookPaymentService;

@RestController
public class BookPaymentController {

    private final BookPaymentService bookPaymentService;

    public BookPaymentController(BookPaymentService bookPaymentService) {
        this.bookPaymentService = bookPaymentService;
    }
}
