package shop.readmecorp.userserverreadme.modules.book.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.readmecorp.userserverreadme.modules.book.entity.Book;
import shop.readmecorp.userserverreadme.modules.book.entity.Heart;
import shop.readmecorp.userserverreadme.modules.book.enums.HeartStatus;
import shop.readmecorp.userserverreadme.modules.book.repository.HeartRepository;
import shop.readmecorp.userserverreadme.modules.book.request.HeartSaveRequest;
import shop.readmecorp.userserverreadme.modules.book.request.HeartUpdateRequest;
import shop.readmecorp.userserverreadme.modules.user.entity.User;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class HeartService {

    private final HeartRepository heartRepository;

    public HeartService(HeartRepository heartRepository) {
        this.heartRepository = heartRepository;
    }

    public Page<Heart> getPage(Pageable pageable) {
        return heartRepository.findAll(pageable);
    }

    public Optional<Heart> getHeart(Integer id) {
        return heartRepository.findById(id);
    }

    @Transactional
    public void saveAndDelete(HeartSaveRequest request, Book book, User user) {
        Optional<Heart> optional = heartRepository.findByUserAndStatusNotAndBook(user, HeartStatus.DELETE, book);
        if (request.getCheck()) {
            if (optional.isEmpty()) {
                heartRepository.save(
                        new Heart(null, user, book, HeartStatus.ACTIVE)
                );
            }
        } else {
            optional.ifPresent(heartRepository::delete);
        }
    }
}
