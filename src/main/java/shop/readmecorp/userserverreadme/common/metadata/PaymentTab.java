package shop.readmecorp.userserverreadme.common.metadata;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.common.enums.PaymentTabType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentTab {
    private String paymentName;

    private String paymentRequestName;
}
