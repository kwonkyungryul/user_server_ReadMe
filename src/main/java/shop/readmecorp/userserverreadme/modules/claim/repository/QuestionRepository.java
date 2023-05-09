package shop.readmecorp.userserverreadme.modules.claim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.readmecorp.userserverreadme.modules.claim.dto.QuestionDTO;
import shop.readmecorp.userserverreadme.modules.claim.entity.Question;
import shop.readmecorp.userserverreadme.modules.claim.enums.ClaimStatus;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findByStatusNotAndUser_Id(ClaimStatus status, Integer user_id);
    Optional<Question> findByStatusNotAndId(ClaimStatus status, Integer id);
}
