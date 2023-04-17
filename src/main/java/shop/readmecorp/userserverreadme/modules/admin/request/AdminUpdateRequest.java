package shop.readmecorp.userserverreadme.modules.admin.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminUpdateRequest {

    private String username;

    private String password;

    private String role;

    private String status;
}
