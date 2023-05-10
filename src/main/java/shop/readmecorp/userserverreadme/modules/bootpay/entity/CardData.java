package shop.readmecorp.userserverreadme.modules.bootpay.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import shop.readmecorp.userserverreadme.modules.bootpay.dto.CardDataDTO;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "CARD_DATA_TB")
public class CardData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유번호")
    private Integer id;
    
    @Comment("PG에서 발급한 고유번호")
    private String tid;

    @Comment("카드 승인 번호")
    private String cardApproveNo;

    @Comment("마스킹 카드번호")
    private String cardNo;

    @Comment("할부 개월수")
    private String cardQuota;

    @Comment("PG사에서 정의한 카드사 코드")
    private String cardCompanyCode;

    @Comment("카드사 이름")
    private String cardCompany;

    @Comment("부트페이 테이블 외래키")
    @OneToOne
    private BootPayMaster bootPayMaster;

    @Builder
    public CardData(Integer id, String tid, String cardApproveNo, String cardNo, String cardQuota, String cardCompanyCode, String cardCompany) {
        this.id = id;
        this.tid = tid;
        this.cardApproveNo = cardApproveNo;
        this.cardNo = cardNo;
        this.cardQuota = cardQuota;
        this.cardCompanyCode = cardCompanyCode;
        this.cardCompany = cardCompany;
    }

    public CardDataDTO toDTO() {
        CardDataDTO dto = new CardDataDTO();
        dto.setId(this.id);
        dto.setTid(this.tid);
        dto.setCardApproveNo(this.cardApproveNo);
        dto.setCardNo(this.cardNo);
        dto.setCardQuota(this.cardQuota);
        dto.setCardCompanyCode(this.cardCompanyCode);
        dto.setCardCompany(this.cardCompany);
        return dto;
    }
}
