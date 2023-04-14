package shop.readmecorp.userserverreadme.modules.publisher.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PublisherSaveRequest {

    private String username;

    private String password;

    private String businessNumber;

    private String businessName;

}
