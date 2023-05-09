package shop.readmecorp.userserverreadme.modules.user.dto;

import lombok.*;
import shop.readmecorp.userserverreadme.common.jpa.RoleType;
import shop.readmecorp.userserverreadme.modules.file.dto.FileInfoDTO;
import shop.readmecorp.userserverreadme.modules.user.entity.User;
import shop.readmecorp.userserverreadme.modules.user.enums.UserStatus;
import shop.readmecorp.userserverreadme.util.DateTimeConverter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    private Integer id;

    private String username;

    private String role;

    private Boolean isMembership;

    private Boolean isAutoPayment;

    public User toEntity() {
        return User.builder()
                .id(id)
                .username(username)
                .role(role)
                .isMembership(isMembership)
                .isAutoPayment(isAutoPayment)
                .build();
    }

    @Builder
    public UserDTO(Integer id, String username, String role, Boolean isMembership, Boolean isAutoPayment) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.isMembership = isMembership;
        this.isAutoPayment = isAutoPayment;
    }
}
