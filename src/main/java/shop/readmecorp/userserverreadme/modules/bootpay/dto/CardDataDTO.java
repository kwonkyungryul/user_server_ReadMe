package shop.readmecorp.userserverreadme.modules.bootpay.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.modules.bootpay.entity.CardData;

@Getter
@Setter
@NoArgsConstructor
public class CardDataDTO {
    private String tid;

    private String cardApproveNo;

    private String cardNo;

    private String cardQuota;

    private String cardCompanyCode;

    private String cardCompany;

    public CardData toEntity() {
        return CardData.builder()
                .id(null)
                .tid(tid)
                .cardApproveNo(cardApproveNo)
                .cardNo(cardNo)
                .cardQuota(cardQuota)
                .cardCompanyCode(cardCompanyCode)
                .cardCompany(cardCompany)
                .build();
    }
}
