package shop.readmecorp.userserverreadme.modules.category.dto;

import lombok.*;
import shop.readmecorp.userserverreadme.common.ValueOfEnum;
import shop.readmecorp.userserverreadme.modules.category.entity.BigCategory;
import shop.readmecorp.userserverreadme.modules.category.entity.SmallCategory;
import shop.readmecorp.userserverreadme.modules.category.enums.BigCategoryType;
import shop.readmecorp.userserverreadme.modules.category.enums.CategoryStatus;
import shop.readmecorp.userserverreadme.modules.category.enums.SmallCategoryType;
import shop.readmecorp.userserverreadme.modules.category.response.BigCategoryResponse;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class BigCategoryDTO {

    @NotNull
    private Integer id;

    @NotBlank
    private String bigCategory;

    @ValueOfEnum(enumClass = CategoryStatus.class, message = "카테고리 상태 값에 이상이 있습니다. 확인해주세요")
    private String status;

    @Builder
    public BigCategoryDTO(Integer id, String bigCategory, String status) {
        this.id = id;
        this.bigCategory = bigCategory;
        this.status = status;
    }

    public BigCategory toEntity() {
        return BigCategory.builder()
                .id(id)
                .bigCategory(BigCategoryType.valueOf(bigCategory))
                .status(CategoryStatus.valueOf(status))
                .build();
    }

    public BigCategoryResponse toResponse() {
        return new BigCategoryResponse(id, bigCategory, status);
    }

}