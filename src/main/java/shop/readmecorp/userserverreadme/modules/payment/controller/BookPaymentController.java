package shop.readmecorp.userserverreadme.modules.payment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.readmecorp.userserverreadme.modules.bootpay.request.BootPaySaveRequest;
import shop.readmecorp.userserverreadme.modules.bootpay.service.BootPayService;
import shop.readmecorp.userserverreadme.modules.payment.dto.ReceiptDTO;
import shop.readmecorp.userserverreadme.modules.payment.service.BookPaymentService;

import java.util.HashMap;

@RestController
@RequestMapping("/payments")
public class BookPaymentController {

    private final BookPaymentService bookPaymentService;

    private final BootPayService bootPayService;

    public BookPaymentController(BookPaymentService bookPaymentService, BootPayService bootPayService) {
        this.bookPaymentService = bookPaymentService;
        this.bootPayService = bootPayService;
    }

    @PostMapping("/callback")
    public ResponseEntity bootPayCallBack(@RequestBody BootPaySaveRequest request) throws Exception{
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        System.out.println(om.writeValueAsString(request));

        bootPayService.save(request);

        var map = new HashMap();
        map.put("success", true);
        return ResponseEntity.ok(map);
    }
}
