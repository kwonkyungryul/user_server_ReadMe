package shop.readmecorp.userserverreadme.modules.bootpay.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import kr.co.bootpay.Bootpay;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import shop.readmecorp.userserverreadme.common.exception.Exception400;
import shop.readmecorp.userserverreadme.common.exception.Exception500;
import shop.readmecorp.userserverreadme.modules.bootpay.request.BootPaySaveRequest;
import shop.readmecorp.userserverreadme.modules.bootpay.service.BootPayService;

import java.util.HashMap;

@RestController
@RequestMapping("/boot-pay")
public class BootPayController {
    private final BootPayService bootPayService;

    @Value("${bootpay.restapikey}")
    private String restApiKey;

    @Value("${bootpay.privatekey}")
    private String privateKey;

    public BootPayController(BootPayService bootPayService) {
        this.bootPayService = bootPayService;
    }

    @PostMapping("/callback")
    public ResponseEntity<HashMap<String, Boolean>> bootPayCallBack(@RequestBody BootPaySaveRequest request) throws Exception{


        bootPayService.save(request);

        Bootpay bootpay = new Bootpay(restApiKey, privateKey);
        HashMap<String, Object> res = bootpay.getAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", res.get("access_token").toString());

        if(res.get("error_code") == null) { //success
            System.out.println("goGetToken success: " + res);
        } else {
            System.out.println("goGetToken false: " + res);
            throw new Exception500("토큰 발급에 실패하였습니다.");
        }

        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        System.out.println(om.writeValueAsString(request));


        var map = new HashMap<String, Boolean>();
        map.put("success", true);

        return ResponseEntity.ok(map);
    }
}
