package shop.readmecorp.userserverreadme.modules.book.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import shop.readmecorp.userserverreadme.modules.book.entity.Heart;
import shop.readmecorp.userserverreadme.modules.book.repository.HeartRepository;
import shop.readmecorp.userserverreadme.modules.book.request.HeartSaveRequest;
import shop.readmecorp.userserverreadme.modules.book.request.HeartUpdateRequest;

import java.util.Optional;

@Service
public class HeartService {

    private HeartRepository heartRepository;

    public HeartService(HeartRepository heartRepository) {
        this.heartRepository = heartRepository;
    }

    public Page<Heart> getPage(Pageable pageable) {
        return heartRepository.findAll(pageable);
    }

    public Optional<Heart> getHeart(Integer id) {
        return heartRepository.findById(id);
    }

    public Heart save(HeartSaveRequest request) {

        return null;
    }

    public Heart update(HeartUpdateRequest request, Heart heart) {
        return null;
    }

    public void delete(Heart heart) {
    }
}
