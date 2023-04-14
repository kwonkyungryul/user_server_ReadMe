package shop.readmecorp.userserverreadme.modules.claim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.readmecorp.userserverreadme.modules.claim.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
