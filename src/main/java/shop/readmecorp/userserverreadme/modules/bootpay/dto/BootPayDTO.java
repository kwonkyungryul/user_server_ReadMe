package shop.readmecorp.userserverreadme.modules.bootpay.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class BootPayDTO {
    BootPayMasterDTO bootPayMaster;

    CardDataDTO cardDataDTO;

    MetadataDTO metadataDTO;

    @Builder
    public BootPayDTO(BootPayMasterDTO bootPayMaster, CardDataDTO cardDataDTO, MetadataDTO metadataDTO) {
        this.bootPayMaster = bootPayMaster;
        this.cardDataDTO = cardDataDTO;
        this.metadataDTO = metadataDTO;
    }
}
