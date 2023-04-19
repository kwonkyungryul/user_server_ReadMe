package shop.readmecorp.userserverreadme.modules.publisher.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import shop.readmecorp.userserverreadme.common.jpa.BaseTime;
import shop.readmecorp.userserverreadme.common.jpa.RoleType;
import shop.readmecorp.userserverreadme.modules.publisher.dto.PublisherDTO;
import shop.readmecorp.userserverreadme.modules.publisher.enums.PublisherStatus;
import shop.readmecorp.userserverreadme.modules.publisher.response.PublisherResponse;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

    @Comment("출판사 가입 시간")
    private LocalDateTime joinTime;

    @Comment("출판사 활성화 상태")
    @Enumerated(EnumType.STRING)
    private PublisherStatus status;

    @Builder
    public Publisher(Integer id, String username, String password, RoleType role, String businessNumber, String businessName, LocalDateTime joinTime, PublisherStatus status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.businessNumber = businessNumber;
        this.businessName = businessName;
        this.joinTime = joinTime;
        this.status = status;
    }

    public PublisherDTO toDTO() {
        return new PublisherDTO(id, username,role.name(),businessNumber, businessName, joinTime.toString(), status.name() );
    }

    public PublisherResponse toResponse() {
        return new PublisherResponse(id, username,role.name(),businessNumber, businessName, joinTime.toString(), status.name());
    }
}
