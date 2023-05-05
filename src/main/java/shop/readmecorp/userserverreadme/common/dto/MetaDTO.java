package shop.readmecorp.userserverreadme.common.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.common.metadata.MainTab;
import shop.readmecorp.userserverreadme.common.metadata.NoticeTypeWrapper;
import shop.readmecorp.userserverreadme.common.metadata.PaymentTab;
import shop.readmecorp.userserverreadme.common.metadata.StorageBoxTab;
import shop.readmecorp.userserverreadme.modules.category.response.CategoryDTO;
import shop.readmecorp.userserverreadme.modules.user.dto.UserDTO;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MetaDTO {
    private CategoryDTO category;

    private UserDTO user;

    private List<StorageBoxTab> storageBoxTabList;

    private List<MainTab> mainTab;

    private List<PaymentTab> paymentTab;

    private List<NoticeTypeWrapper> noticeTypeWrapper;

    @Builder
    public MetaDTO(CategoryDTO category, UserDTO user, List<StorageBoxTab> storageBoxTabList, List<MainTab> mainTab, List<PaymentTab> paymentTab, List<NoticeTypeWrapper> noticeTypeWrapper) {
        this.category = category;
        this.user = user;
        this.storageBoxTabList = storageBoxTabList;
        this.mainTab = mainTab;
        this.paymentTab = paymentTab;
        this.noticeTypeWrapper = noticeTypeWrapper;
    }
}
