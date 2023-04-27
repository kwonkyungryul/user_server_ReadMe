package shop.readmecorp.userserverreadme.modules.bootpay.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Metadata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // TODO 컬럼 추가해야함.
    private String test;

    @Comment("부트페이 테이블 외래키")
    @OneToOne
    private BootPayMaster bootPayMaster;

    @Builder
    public Metadata(Integer id, String test) {
        this.id = id;
        this.test = test;
    }
}
