package shop.readmecorp.userserverreadme.modules.bootpay.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.modules.bootpay.dto.CardDataDTO;
import shop.readmecorp.userserverreadme.modules.bootpay.dto.MetadataDTO;
import shop.readmecorp.userserverreadme.modules.bootpay.entity.CardData;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BootPaySaveRequest {
    @JsonProperty("receipt_id")
    private String receiptId;

    @JsonProperty("order_id")
    private String orderId;

    @JsonProperty("price")
    private int price;

    @JsonProperty("tax_free")
    private int taxFree;

    @JsonProperty("cancelled_price")
    private int cancelledPrice;

    @JsonProperty("cancelled_tax_free")
    private int cancelledTaxFree;

    @JsonProperty("order_name")
    private String orderName;

    @JsonProperty("company_name")
    private String companyName;

    @JsonProperty("gateway_url")
    private String gatewayUrl;

    @JsonProperty("metadata")
    private MetadataDTO metadata;

    private boolean sandbox;

    private String pg;

    private String method;

    @JsonProperty("method_symbol")
    private String methodSymbol;

    @JsonProperty("method_origin")
    private String methodOrigin;

    @JsonProperty("method_origin_symbol")
    private String methodOriginSymbol;

    @JsonProperty("purchased_at")
    private OffsetDateTime purchasedAt;

    @JsonProperty("cancelled_at")
    private OffsetDateTime cancelledAt;

    @JsonProperty("requested_at")
    private OffsetDateTime requestedAt;

    @JsonProperty("status_locale")
    private String statusLocale;

    @JsonProperty("receipt_url")
    private String receiptUrl;

    private int status;

    @JsonProperty("card_data")
    private CardDataDTO cardData;

}