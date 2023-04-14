package shop.readmecorp.userserverreadme.modules.history.controller;

import org.springframework.web.bind.annotation.RestController;
import shop.readmecorp.userserverreadme.modules.history.service.SearchHistoryService;

@RestController
public class SearchHistoryController {

    private SearchHistoryService searchHistoryService;

    public SearchHistoryController(SearchHistoryService searchHistoryService) {
        this.searchHistoryService = searchHistoryService;
    }
}
