package shop.readmecorp.userserverreadme.modules.category.dto;

import lombok.*;
import shop.readmecorp.userserverreadme.common.ValueOfEnum;
import shop.readmecorp.userserverreadme.modules.category.entity.BigCategory;
import shop.readmecorp.userserverreadme.modules.category.entity.SmallCategory;
import shop.readmecorp.userserverreadme.modules.category.enums.BigCategoryType;
import shop.readmecorp.userserverreadme.modules.category.enums.CategoryStatus;
import shop.readmecorp.userserverreadme.modules.category.enums.SmallCategoryType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class SmallCategoryDTO {

    @NotNull
    private Integer id;

    @NotBlank
    private String smallCategory;

    @Builder
    public SmallCategoryDTO(Integer id, String smallCategory) {
        this.id = id;
        this.smallCategory = smallCategory;
    }

    public SmallCategory toEntity() {
        return SmallCategory.builder()
                .id(id)
                .smallCategory(SmallCategoryType.valueOf(smallCategory))
                .build();
    }

}