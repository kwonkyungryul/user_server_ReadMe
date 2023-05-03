package shop.readmecorp.userserverreadme.modules.user.entity;

import lombok.*;
import org.hibernate.annotations.Comment;
import shop.readmecorp.userserverreadme.common.jpa.BaseTime;
import shop.readmecorp.userserverreadme.common.jpa.RoleType;
import shop.readmecorp.userserverreadme.modules.file.entity.FileInfo;
import shop.readmecorp.userserverreadme.modules.user.dto.UserDTO;
import shop.readmecorp.userserverreadme.modules.user.enums.UserStatus;
import shop.readmecorp.userserverreadme.modules.user.response.UserResponse;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;

// checkpoint : user model
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "USER_TB")
public class User extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유번호")
    private Integer id;

    @Comment("아이디")
    private String username;

    @Comment("비밀번호")
    private String password;

    @Comment("권한")
    @Enumerated(EnumType.STRING)
    private RoleType role;

    @Comment("멤버십 가입 여부")
    private Boolean isMembership;

    @Comment("자동 결제 여부")
    private Boolean isAutoPayment;

    //TODO 이거 써도되나?
    @Comment("유저 가입 시간")
    private LocalDateTime joinTime;

    @Comment("유저 활성화 상태")
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Builder
    public User(Integer id, String username,String password,Boolean isMembership, Boolean isAutoPayment, LocalDateTime joinTime, FileInfo fileInfo) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = RoleType.USER;
        this.isMembership = isMembership;
        this.isAutoPayment = isAutoPayment;
        this.joinTime = joinTime;
        this.status = UserStatus.ACTIVE;
    }

    public UserDTO toDTO() {
        return new UserDTO(id, username,role.name(), isMembership,isAutoPayment,joinTime.toString(), status.name());
    }

    public UserResponse toResponse() {
        return new UserResponse(id, username,role.name(), isMembership,isAutoPayment, joinTime.toString(), status.name());
    }
}
