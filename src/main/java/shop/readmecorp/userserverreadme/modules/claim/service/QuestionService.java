package shop.readmecorp.userserverreadme.modules.claim.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.readmecorp.userserverreadme.modules.claim.dto.AnswerDTO;
import shop.readmecorp.userserverreadme.modules.claim.dto.QuestionDTO;
import shop.readmecorp.userserverreadme.modules.claim.entity.Answer;
import shop.readmecorp.userserverreadme.modules.claim.entity.Question;
import shop.readmecorp.userserverreadme.modules.claim.enums.ClaimStatus;
import shop.readmecorp.userserverreadme.modules.claim.repository.AnswerRepository;
import shop.readmecorp.userserverreadme.modules.claim.repository.QuestionRepository;
import shop.readmecorp.userserverreadme.modules.claim.request.QuestionSaveRequest;
import shop.readmecorp.userserverreadme.modules.publisher.entity.Publisher;
import shop.readmecorp.userserverreadme.modules.user.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public QuestionService(QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }


    public List<QuestionDTO> getList(User user) {
        List<Question> questions = questionRepository.findByStatusNotAndUser_Id(ClaimStatus.DELETE, user.getId());
        List<Answer> answerList = answerRepository.findByQuestionInAndStatusNot(questions, ClaimStatus.DELETE);


        return questions
                .stream()
                .map(question -> {
                    QuestionDTO questionDTO = question.toDTO();
                    List<Answer> filterList = answerList.stream()
                            .filter(answer -> answer.getQuestion().getId().intValue() == questionDTO.getId())
                            .collect(Collectors.toList());
                    if (filterList.size() > 0) {
                        questionDTO.setAnswer(filterList.get(0).toDTO());
                    }
                    return questionDTO;
                })
                .collect(Collectors.toList());
    }

    public Optional<Question> getDetail(Integer id) {
        return questionRepository.findByStatusNotAndId(ClaimStatus.DELETE, id);
    }

    @Transactional
    public QuestionDTO save(User user, QuestionSaveRequest request, Publisher publisher){
        return questionRepository.save(
                new Question(
                null, user.getRole(), user, publisher, request.getTitle(), request.getContent(), ClaimStatus.WAIT
                )
        ).toDTO();
    }

    @Transactional
    public void delete(Question question){
        question.setStatus(ClaimStatus.DELETE);
        questionRepository.save(question);
    }
}
