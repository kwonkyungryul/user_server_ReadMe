package shop.readmecorp.userserverreadme.modules.bootpay.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.modules.bootpay.entity.Metadata;

@Getter
@Setter
@NoArgsConstructor
public class MetadataDTO {
    private String test;

    public Metadata toEntity() {
        return Metadata.builder()
                .id(null)
                .test(test)
                .build();
    }
}
