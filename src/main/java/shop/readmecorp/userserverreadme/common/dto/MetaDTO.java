package shop.readmecorp.userserverreadme.common.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.modules.category.dto.BigCategoryDTO;
import shop.readmecorp.userserverreadme.modules.user.dto.UserDTO;
import shop.readmecorp.userserverreadme.modules.user.dto.UserInfoDTO;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MetaDTO {
    private List<BigCategoryDTO> bigCategory;

    private UserInfoDTO user;

    private List<CommonDTO> storageBoxTabs;

    private List<CommonDTO> mainTabs;

    private List<CommonDTO> paymentTabs;

    private List<String> notificationTypes;

    @Builder
    public MetaDTO(List<BigCategoryDTO> bigCategory, UserInfoDTO user, List<CommonDTO> storageBoxTabs, List<CommonDTO> mainTabs, List<CommonDTO> paymentTabs, List<String> notificationTypes) {
        this.bigCategory = bigCategory;
        this.user = user;
        this.storageBoxTabs = storageBoxTabs;
        this.mainTabs = mainTabs;
        this.paymentTabs = paymentTabs;
        this.notificationTypes = notificationTypes;
    }
}
