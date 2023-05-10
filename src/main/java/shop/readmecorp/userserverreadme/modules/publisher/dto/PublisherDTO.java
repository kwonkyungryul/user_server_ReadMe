package shop.readmecorp.userserverreadme.modules.publisher.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import shop.readmecorp.userserverreadme.modules.common.jpa.RoleType;
import shop.readmecorp.userserverreadme.modules.publisher.entity.Publisher;
import shop.readmecorp.userserverreadme.util.DateTimeConverter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PublisherDTO {

    @NotNull
    private Integer id;

    @NotBlank
    private String username;

    @NotBlank
    private String role;

    @NotBlank
    private String businessNumber;

    @NotBlank
    private String businessName;

    @NotBlank
    private String joinTime;


    public Publisher toEntity() {
        return Publisher.builder()
                .id(id)
                .username(username)
                .role(RoleType.valueOf(role))
                .businessNumber(businessNumber)
                .businessName(businessName)
                .joinTime(DateTimeConverter.stringToLocalDateTime(joinTime))
                .build();
    }
}