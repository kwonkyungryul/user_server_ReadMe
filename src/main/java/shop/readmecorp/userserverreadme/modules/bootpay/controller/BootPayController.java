package shop.readmecorp.userserverreadme.modules.bootpay.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.readmecorp.userserverreadme.modules.bootpay.service.BootPayService;

@RestController
public class BootPayController {
    private final BootPayService bootPayService;

    public BootPayController(BootPayService bootPayService) {
        this.bootPayService = bootPayService;
    }
}
