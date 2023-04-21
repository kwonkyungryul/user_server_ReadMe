package shop.readmecorp.userserverreadme.modules.admin.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminResponse {
    private Integer id;

    private String username;

    private String role;

    private String status;
}
