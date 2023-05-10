package shop.readmecorp.userserverreadme.modules.user.dto;

import lombok.*;
import shop.readmecorp.userserverreadme.modules.user.entity.User;

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
