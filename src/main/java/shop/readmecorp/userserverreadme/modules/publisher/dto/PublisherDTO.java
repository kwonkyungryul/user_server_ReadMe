package shop.readmecorp.userserverreadme.modules.publisher.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.common.jpa.RoleType;
import shop.readmecorp.userserverreadme.modules.publisher.entity.Publisher;
import shop.readmecorp.userserverreadme.modules.publisher.enums.PublisherStatus;
import shop.readmecorp.userserverreadme.modules.user.enums.UserStatus;
import shop.readmecorp.userserverreadme.util.DateTimeConverter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PublisherDTO {

    private Integer id;

    private String username;

    private String password;

    private String role;

    private String businessNumber;

    private String businessName;

    //TODO 이거 써도되나?
    private String joinTime;

    private String status;

    public Publisher toEntity() {
        return Publisher.builder()
                .id(id)
                .username(username)
                .password(password)
                .role(RoleType.PUBLISHER)
                .businessNumber(businessNumber)
                .businessName(businessName)
                .joinTime(DateTimeConverter.stringToLocalDateTime(joinTime))
                .status(PublisherStatus.valueOf(status))
                .build();
    }

}