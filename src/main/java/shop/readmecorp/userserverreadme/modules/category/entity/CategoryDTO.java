package shop.readmecorp.userserverreadme.modules.category.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.modules.category.dto.BigCategoryDTO;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {
    private List<BigCategoryDTO> bigCategory;

    @Builder
    public CategoryDTO(List<BigCategoryDTO> bigCategory) {
        this.bigCategory = bigCategory;
    }


}
