package shop.readmecorp.userserverreadme.modules.user.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import shop.readmecorp.userserverreadme.modules.common.jpa.BaseTime;
import shop.readmecorp.userserverreadme.modules.notification.enums.OSType;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "USER_FCM_TB")
public class UserFcm extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유번호")
    private Integer id;

    @Comment("유저 정보")
    @ManyToOne
    private User user;

    @Comment("OS 타입")
    @Enumerated(EnumType.STRING)
    private OSType osType;

    @Comment("멤버십 가입 여부")
    private String token;

    @Builder
    public UserFcm(User user, OSType osType, String token) {
        this.user = user;
        this.osType = osType;
        this.token = token;
    }

}
