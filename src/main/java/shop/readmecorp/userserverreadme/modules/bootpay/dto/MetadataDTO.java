package shop.readmecorp.userserverreadme.modules.bootpay.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.modules.bootpay.entity.Metadata;
import shop.readmecorp.userserverreadme.modules.payment.enums.PaymentType;

@Getter
@Setter
@NoArgsConstructor
public class MetadataDTO {
    private Integer id;

    private Integer paymentId;

    private String paymentType;

    public Metadata toEntity() {
        return Metadata.builder()
                .id(null)
                .paymentId(paymentId)
                .type(PaymentType.valueOf(paymentType))
                .build();
    }
}
