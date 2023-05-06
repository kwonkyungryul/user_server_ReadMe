package shop.readmecorp.userserverreadme.modules.category.dto;

import lombok.*;
import shop.readmecorp.userserverreadme.modules.category.entity.BigCategory;
import shop.readmecorp.userserverreadme.modules.category.enums.BigCategoryType;
import shop.readmecorp.userserverreadme.modules.category.response.BigCategoryResponse;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BigCategoryDTO {

    private Integer id;

    private String name;

    private List<SmallCategoryDTO> smallCategory;

    @Builder
    public BigCategoryDTO(Integer id, String name, List<SmallCategoryDTO> smallCategory) {
        this.id = id;
        this.name = name;
        this.smallCategory = smallCategory;
    }

    public BigCategory toEntity() {
        return BigCategory.builder()
                .id(id)
                .bigCategory(BigCategoryType.valueOf(name))
                .build();
    }

    public BigCategoryResponse toResponse() {
        return new BigCategoryResponse(id, name);
    }

}