package shop.readmecorp.userserverreadme.modules.publisher.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import shop.readmecorp.userserverreadme.common.jpa.BaseTime;
import shop.readmecorp.userserverreadme.common.jpa.RoleType;
import shop.readmecorp.userserverreadme.modules.publisher.enums.PublisherStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PUBLISHER_TB")
public class Publisher extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유번호")
    private Integer id;

    @Comment("아이디")
    private String username;

    @Comment("비밀번호")
    private String password;

    @Enumerated(EnumType.STRING)
    @Comment("권한")
    private RoleType role;

    @Comment("사업자 번호")
    private String businessNumber;

    @Comment("사업자 이름")
    private String businessName;

    //TODO 이거 써도되나?
    @Comment("출판사 가입 시간")
    private LocalDateTime joinTime;

    @Comment("출판사 활성화 상태")
    @Enumerated(EnumType.STRING)
    private PublisherStatus status;
}
