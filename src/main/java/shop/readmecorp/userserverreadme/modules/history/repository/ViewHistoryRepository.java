package shop.readmecorp.userserverreadme.modules.history.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.readmecorp.userserverreadme.modules.history.entity.ViewHistory;

public interface ViewHistoryRepository extends JpaRepository<ViewHistory, Integer> {
}
