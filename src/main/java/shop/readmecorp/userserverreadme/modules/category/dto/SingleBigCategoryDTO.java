package shop.readmecorp.userserverreadme.modules.category.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.modules.category.entity.BigCategory;
import shop.readmecorp.userserverreadme.modules.category.enums.BigCategoryType;
import shop.readmecorp.userserverreadme.modules.category.response.BigCategoryResponse;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SingleBigCategoryDTO {

    private Integer id;

    private String name;


    @Builder
    public SingleBigCategoryDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
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