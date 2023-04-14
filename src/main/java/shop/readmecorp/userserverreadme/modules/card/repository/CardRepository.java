package shop.readmecorp.userserverreadme.modules.card.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.readmecorp.userserverreadme.modules.card.entity.Card;

public interface CardRepository extends JpaRepository<Card, Integer> {
}
