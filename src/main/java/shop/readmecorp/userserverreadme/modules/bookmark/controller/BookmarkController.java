package shop.readmecorp.userserverreadme.modules.bookmark.controller;

import org.springframework.web.bind.annotation.RestController;
import shop.readmecorp.userserverreadme.modules.bookmark.service.BookmarkService;

@RestController
public class BookmarkController {

    private final BookmarkService bookmartService;

    public BookmarkController(BookmarkService bookmartService) {
        this.bookmartService = bookmartService;
    }
}
