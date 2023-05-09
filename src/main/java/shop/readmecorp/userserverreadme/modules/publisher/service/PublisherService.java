package shop.readmecorp.userserverreadme.modules.publisher.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.readmecorp.userserverreadme.modules.publisher.entity.Publisher;
import shop.readmecorp.userserverreadme.modules.publisher.repository.PublisherRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PublisherService {

    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public Optional<Publisher> getPublisher(Integer id) {
        return publisherRepository.findById(id);
    }


}
