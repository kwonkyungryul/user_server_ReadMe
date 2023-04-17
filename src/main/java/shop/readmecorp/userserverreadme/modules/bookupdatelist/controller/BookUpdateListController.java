package shop.readmecorp.userserverreadme.modules.bookupdatelist.controller;

import org.springframework.web.bind.annotation.RestController;
import shop.readmecorp.userserverreadme.modules.bookdeletelist.service.BookDeleteListService;

@RestController
public class BookUpdateListController {

    private final BookDeleteListService bookDeleteListService;

    public BookUpdateListController(BookDeleteListService bookDeleteListService) {
        this.bookDeleteListService = bookDeleteListService;
    }
}
