package shop.readmecorp.userserverreadme.modules.heart.controller;

import org.springframework.web.bind.annotation.RestController;
import shop.readmecorp.userserverreadme.modules.heart.service.HeartService;

@RestController
public class HeartController {

    private HeartService heartService;

    public HeartController(HeartService heartService) {
        this.heartService = heartService;
    }
}
