package shop.readmecorp.userserverreadme.modules.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.modules.file.dto.FileInfoDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Integer id;

    private String username;

    private String password;

    private String role;

    private Boolean isMembership;

    private Boolean isAutoPayment;

    //TODO 이거 써도되나?
    private String joinTime;

    private FileInfoDTO fileInfo;

    private String status;


}
