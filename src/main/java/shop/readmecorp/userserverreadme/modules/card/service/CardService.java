package shop.readmecorp.userserverreadme.modules.card.service;

import org.springframework.stereotype.Service;
import shop.readmecorp.userserverreadme.modules.card.repository.CardRepository;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }
}
