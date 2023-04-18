package shop.readmecorp.userserverreadme.modules.category.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.modules.category.entity.Category;
import shop.readmecorp.userserverreadme.modules.category.enums.BigCategoryType;
import shop.readmecorp.userserverreadme.modules.category.enums.CategoryStatus;
import shop.readmecorp.userserverreadme.modules.category.enums.SmallCategoryType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private Integer id;

    private String bigCategory;

    private String smallCategory;

    private String status;

    public Category toEntity() {
        return Category.builder()
                .id(1)
                .bigCategory(BigCategoryType.valueOf(bigCategory))
                .smallCategory(SmallCategoryType.valueOf(smallCategory))
                .status(CategoryStatus.valueOf(status))
                .build();
    }
}