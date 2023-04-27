package shop.readmecorp.userserverreadme.modules.bootpay.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "BOOTPAY_MASTER_TB")
public class BootPayMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유번호")
    private Integer id;
    
    @Comment("테스트 모드 여부")
    private Boolean sandbox;

    @Comment("PG사")
    private String pg;

    @Comment("결제 수단")
    private String method;

    @Comment("결제 상태값")
    private Integer status;

    @Comment("고유 영수증 번호")
    private String receiptId;

    @Comment("주문 번호")
    private String orderId;

    @Comment("결제 금액")
    private Integer price;

    @Comment("비과세 금액")
    private Integer taxFree;

    @Comment("결제 취소 금액")
    private Integer cancelledPrice;

    @Comment("비과세 취소 금액")
    private Integer cancelledTaxFree;

    @Comment("아이템 이름")
    private String orderName;

    @Comment("가맹정 이름")
    private String companyName;

    @Comment("PG로 요청된 부트페이 URL")
    private String gatewayUrl;

    @Comment("결제 수단 영문 SYMBOL")
    private String methodSymbol;

    @Comment("원 결제수단 이름")
    private String methodOrigin;

    @Comment("원 결제수단 영문 SYMBOL")
    private String methodOriginSymbol;

    @Comment("결제 완료 시간")
    private OffsetDateTime purchasedAt;

    @Comment("결제 취소 시간")
    private OffsetDateTime cancelledAt;

    @Comment("결제 요청 시간")
    private OffsetDateTime requestedAt;

    @Comment("결제 상태")
    private String statusLocale;

    @Comment("결제 영수증 URL")
    private String receiptUrl;

    @Builder
    public BootPayMaster(Integer id, Boolean sandbox, String pg, String method, Integer status, String receiptId, String orderId, Integer price, Integer taxFree, Integer cancelledPrice, Integer cancelledTaxFree, String orderName, String companyName, String gatewayUrl, String methodSymbol, String methodOrigin, String methodOriginSymbol, OffsetDateTime purchasedAt, OffsetDateTime cancelledAt, OffsetDateTime requestedAt, String statusLocale, String receiptUrl) {
        this.id = id;
        this.sandbox = sandbox;
        this.pg = pg;
        this.method = method;
        this.status = status;
        this.receiptId = receiptId;
        this.orderId = orderId;
        this.price = price;
        this.taxFree = taxFree;
        this.cancelledPrice = cancelledPrice;
        this.cancelledTaxFree = cancelledTaxFree;
        this.orderName = orderName;
        this.companyName = companyName;
        this.gatewayUrl = gatewayUrl;
        this.methodSymbol = methodSymbol;
        this.methodOrigin = methodOrigin;
        this.methodOriginSymbol = methodOriginSymbol;
        this.purchasedAt = purchasedAt;
        this.cancelledAt = cancelledAt;
        this.requestedAt = requestedAt;
        this.statusLocale = statusLocale;
        this.receiptUrl = receiptUrl;
    }
}
