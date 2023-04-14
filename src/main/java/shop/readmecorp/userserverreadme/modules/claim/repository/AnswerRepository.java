package shop.readmecorp.userserverreadme.modules.claim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.readmecorp.userserverreadme.modules.claim.entity.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}
