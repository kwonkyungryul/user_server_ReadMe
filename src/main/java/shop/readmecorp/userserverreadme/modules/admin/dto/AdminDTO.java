package shop.readmecorp.userserverreadme.modules.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminDTO {

    private Integer id;

    private String username;

    private String role;

    private String status;
}
