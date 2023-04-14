package shop.readmecorp.userserverreadme.modules.history.service;

import org.springframework.stereotype.Service;
import shop.readmecorp.userserverreadme.modules.history.repository.SearchHistoryRepository;

@Service
public class SearchHistoryService {

    private SearchHistoryRepository searchHistoryRepository;

    public SearchHistoryService(SearchHistoryRepository searchHistoryRepository) {
        this.searchHistoryRepository = searchHistoryRepository;
    }
}
