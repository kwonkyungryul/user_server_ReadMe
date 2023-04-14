package shop.readmecorp.userserverreadme.modules.card.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardUpdateRequest {

    private String cardNum;

    private String cardPassword;

    private String exp;

    private Integer cvc;

    private Boolean isAutoPayment;

    private String status;

}
