package shop.readmecorp.userserverreadme.modules.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.common.jpa.RoleType;
import shop.readmecorp.userserverreadme.modules.file.dto.FileInfoDTO;
import shop.readmecorp.userserverreadme.modules.user.entity.User;
import shop.readmecorp.userserverreadme.modules.user.enums.UserStatus;
import shop.readmecorp.userserverreadme.util.DateTimeConverter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotNull(message = "유저 ID가 없습니다.")
    private Integer id;

    private String username;

    private String role;

    private Boolean isMembership;

    private Boolean isAutoPayment;

    //TODO 이거 써도되나?
    private String joinTime;

    private FileInfoDTO fileInfo;

    private String status;

    public User toEntity() {
        return User.builder()
                .username(username)
                .role(RoleType.valueOf(role))
                .isMembership(isMembership)
                .isAutoPayment(isAutoPayment)
                .joinTime(DateTimeConverter.stringToLocalDateTime(joinTime))
                .fileInfo(fileInfo.toEntity())
                .status(UserStatus.valueOf(status))
                .build();
    }


}
