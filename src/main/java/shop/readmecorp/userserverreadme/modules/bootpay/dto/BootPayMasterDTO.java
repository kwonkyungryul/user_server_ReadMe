package shop.readmecorp.userserverreadme.modules.bootpay.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BootPayMasterDTO {
    private Boolean sandbox;

    private String pg;

    private String method;

    private Integer status;

    private String receiptId;

    private String orderId;

    private Integer price;

    private Integer taxFree;

    private Integer cancelledPrice;

    private Integer cancelledTaxFree;

    private String orderName;

    private String companyName;

    private String gatewayUrl;

    private String methodSymbol;

    private String methodOrigin;

    private String methodOriginSymbol;

    private OffsetDateTime purchasedAt;

    private OffsetDateTime cancelledAt;

    private OffsetDateTime requestedAt;

    private String statusLocale;

    private String receiptUrl;
}
