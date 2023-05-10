package shop.readmecorp.userserverreadme.modules.bootpay.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import kr.co.bootpay.Bootpay;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.readmecorp.userserverreadme.auth.session.MyUserDetails;
import shop.readmecorp.userserverreadme.modules.common.exception.Exception400;
import shop.readmecorp.userserverreadme.modules.common.exception.Exception500;
import shop.readmecorp.userserverreadme.modules.bootpay.dto.BootPayDTO;
import shop.readmecorp.userserverreadme.modules.bootpay.request.BootPaySaveRequest;
import shop.readmecorp.userserverreadme.modules.bootpay.service.BootPayService;
import shop.readmecorp.userserverreadme.modules.payment.dto.BookPaymentDTO;
import shop.readmecorp.userserverreadme.modules.payment.service.BookPaymentService;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/boot-pay")
public class BootPayController {
    private final BootPayService bootPayService;

    private final BookPaymentService bookPaymentService;

    @Value("${bootpay.restapikey}")
    private String restApiKey;

    @Value("${bootpay.privatekey}")
    private String privateKey;

    public BootPayController(BootPayService bootPayService, BookPaymentService bookPaymentService) {
        this.bootPayService = bootPayService;
        this.bookPaymentService = bookPaymentService;
    }

    @PostMapping("/callback")
    public ResponseEntity<HashMap<String, Boolean>> bootPayCallBack(
            @RequestBody BootPaySaveRequest request,
            @AuthenticationPrincipal MyUserDetails myUserDetails
            ) throws Exception {


        BootPayDTO bootPayDTO = bootPayService.save(request);

        // 토큰 검증
        Bootpay bootpay = new Bootpay(restApiKey, privateKey);
        HashMap<String, Object> res = bootpay.getAccessToken();

        if(res.get("error_code") == null) { //success
            System.out.println("goGetToken success: " + res);
        } else {
            System.out.println("goGetToken false: " + res);
            throw new Exception500("토큰 발급에 실패하였습니다.");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", res.get("access_token").toString());


        // 영수증 ID 검증
        String receiptId = request.getReceiptId();
        HashMap<String, Object> receiptConfirm = bootpay.confirm(receiptId);
        if (receiptConfirm.get("error_code") == null) {
            System.out.println("confirm success: " + res);
        } else {
            System.out.println("confirm false: " + res);
        }

        Integer paymentId = request.getMetadata().getPaymentId();
        List<BookPaymentDTO> bookPaymentList = bookPaymentService.getBookPayments(paymentId, myUserDetails.getUser());
        bookPaymentList.forEach(bookPaymentDTO -> {
            Integer paymentPk = bootPayDTO.getMetadataDTO().getPaymentId();
            String paymentType = bootPayDTO.getMetadataDTO().getPaymentType();

            if (!paymentPk.equals(request.getMetadata().getPaymentId()) && !paymentType.equals(request.getMetadata().getPaymentType())) {
                throw new Exception400("결제 타입이 일치하지 않습니다.");
            }

            if (bookPaymentDTO.getPrice() != request.getPrice()) {
                throw new Exception400("결제 금액이 일치하지 않습니다.");
            }
        });



//        List<Integer> bookPaymentIds = bookPaymentList.stream().map(BookPaymentDTO::getId).collect(Collectors.toList());







        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        System.out.println(om.writeValueAsString(request));


        var map = new HashMap<String, Boolean>();
        map.put("success", true);

        return ResponseEntity.ok(map);
    }
}
