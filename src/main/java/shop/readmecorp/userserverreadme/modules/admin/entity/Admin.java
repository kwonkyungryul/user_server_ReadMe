package shop.readmecorp.userserverreadme.modules.admin.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import shop.readmecorp.userserverreadme.common.jpa.BaseTime;
import shop.readmecorp.userserverreadme.common.jpa.RoleType;
import shop.readmecorp.userserverreadme.modules.admin.dto.AdminDTO;
import shop.readmecorp.userserverreadme.modules.admin.enums.AdminStatus;
import shop.readmecorp.userserverreadme.modules.admin.response.AdminResponse;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "ADMIN_TB")
public class Admin extends BaseTime {
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

    @Comment("유저 활성화 상태")
    @Enumerated(EnumType.STRING)
    private AdminStatus status;

    @Builder
    public Admin(Integer id, String username, String password, RoleType role, AdminStatus status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public AdminDTO toDTO() {
        return new AdminDTO(id, username,role.name(), status.name()  );
    }

    public AdminResponse toResponse() {
        return new AdminResponse(id, username,role.name(), status.name());
    }
}
