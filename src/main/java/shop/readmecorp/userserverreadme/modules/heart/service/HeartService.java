package shop.readmecorp.userserverreadme.modules.heart.service;

import org.springframework.stereotype.Service;
import shop.readmecorp.userserverreadme.modules.heart.repository.HeartRepository;

@Service
public class HeartService {

    private HeartRepository heartRepository;

    public HeartService(HeartRepository heartRepository) {
        this.heartRepository = heartRepository;
    }
}
