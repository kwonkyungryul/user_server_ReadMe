package shop.readmecorp.userserverreadme.modules.publisher.service;

import org.springframework.stereotype.Service;
import shop.readmecorp.userserverreadme.modules.publisher.repository.PublisherRepository;

@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }
}
