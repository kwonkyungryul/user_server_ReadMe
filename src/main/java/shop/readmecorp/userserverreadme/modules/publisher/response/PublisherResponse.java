package shop.readmecorp.userserverreadme.modules.publisher.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PublisherResponse {

    private Integer id;

    private String username;

    private String role;

    private String businessNumber;

    private String businessName;

    //TODO 이거 써도되나?
    private String joinTime;

}
