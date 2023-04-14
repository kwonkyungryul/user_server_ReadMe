package shop.readmecorp.userserverreadme.modules.history.service;

import org.springframework.stereotype.Service;
import shop.readmecorp.userserverreadme.modules.history.repository.ViewHistoryRepository;

@Service
public class ViewHistoryService {

    private ViewHistoryRepository viewHistoryRepository;

    public ViewHistoryService(ViewHistoryRepository viewHistoryRepository) {
        this.viewHistoryRepository = viewHistoryRepository;
    }
}
