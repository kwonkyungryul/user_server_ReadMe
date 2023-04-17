package shop.readmecorp.userserverreadme.modules.bookdeletelist.service;

import org.springframework.stereotype.Service;
import shop.readmecorp.userserverreadme.modules.bookdeletelist.repository.BookDeleteListRepository;

@Service
public class BookDeleteListService {

    private final BookDeleteListRepository bookDeleteListRepository;

    public BookDeleteListService(BookDeleteListRepository bookDeleteListRepository) {
        this.bookDeleteListRepository = bookDeleteListRepository;
    }
}
