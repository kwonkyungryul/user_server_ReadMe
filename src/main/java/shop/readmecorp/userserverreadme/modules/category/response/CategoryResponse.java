package shop.readmecorp.userserverreadme.modules.category.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageImpl;
import shop.readmecorp.userserverreadme.modules.book.dto.BookDTO;
import shop.readmecorp.userserverreadme.modules.category.dto.BigCategoryDTO;
import shop.readmecorp.userserverreadme.modules.category.dto.SmallCategoryDTO;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CategoryResponse {
    private List<BigCategoryDTO> bigCategory;

    @Builder
    public CategoryResponse(List<BigCategoryDTO> bigCategory) {
        this.bigCategory = bigCategory;
    }


}
