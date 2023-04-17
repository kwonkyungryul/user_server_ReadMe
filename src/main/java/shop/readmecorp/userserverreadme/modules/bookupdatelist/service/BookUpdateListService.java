package shop.readmecorp.userserverreadme.modules.bookupdatelist.service;

import org.springframework.stereotype.Service;
import shop.readmecorp.userserverreadme.modules.bookupdatelist.repository.BookUpdateListRepository;

@Service
public class BookUpdateListService {

    private final BookUpdateListRepository bookUpdateListRepository;

    public BookUpdateListService(BookUpdateListRepository bookUpdateListRepository) {
        this.bookUpdateListRepository = bookUpdateListRepository;
    }
}
