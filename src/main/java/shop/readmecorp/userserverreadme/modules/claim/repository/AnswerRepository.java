package shop.readmecorp.userserverreadme.modules.claim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.readmecorp.userserverreadme.modules.claim.entity.Answer;
import shop.readmecorp.userserverreadme.modules.claim.entity.Question;
import shop.readmecorp.userserverreadme.modules.claim.enums.ClaimStatus;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    List<Answer> findByQuestionInAndStatusNot(List<Question> questions, ClaimStatus status);
}
