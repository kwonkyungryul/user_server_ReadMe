package shop.readmecorp.userserverreadme.modules.bootpay.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import shop.readmecorp.userserverreadme.modules.bootpay.dto.MetadataDTO;
import shop.readmecorp.userserverreadme.modules.payment.enums.PaymentType;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "META_DATA_TB")
public class Metadata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유번호")
    private Integer id;

    // TODO 컬럼 추가해야함.
    @Comment("결제 번호")
    private Integer paymentId;
    
    @Comment("결제 타입")
    @Enumerated(EnumType.STRING)
    private PaymentType type;

    @Comment("부트페이 테이블 외래키")
    @OneToOne
    private BootPayMaster bootPayMaster;

    @Builder
    public Metadata(Integer id, Integer paymentId, PaymentType type, BootPayMaster bootPayMaster) {
        this.id = id;
        this.paymentId = paymentId;
        this.type = type;
        this.bootPayMaster = bootPayMaster;
    }

    public MetadataDTO toDTO() {
        MetadataDTO dto = new MetadataDTO();
        dto.setId(id);
        dto.setPaymentId(paymentId);
        dto.setPaymentType(type.name());
        return dto;
    }
}
