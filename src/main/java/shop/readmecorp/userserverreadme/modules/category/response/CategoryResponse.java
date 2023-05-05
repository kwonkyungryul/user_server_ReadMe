package shop.readmecorp.userserverreadme.modules.category.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageImpl;
import shop.readmecorp.userserverreadme.modules.book.dto.BookDTO;
import shop.readmecorp.userserverreadme.modules.category.dto.BigCategoryDTO;
import shop.readmecorp.userserverreadme.modules.category.dto.SmallCategoryDTO;

import java.util.List;

@Getter
@Setter
public class CategoryResponse {
    private List<BigCategoryDTO> bigCategory;

    private List<SmallCategoryDTO> smallCategory;

    private PageImpl<BookDTO> books;

    @Builder
    public CategoryResponse(List<BigCategoryDTO> bigCategory, List<SmallCategoryDTO> smallCategory, PageImpl<BookDTO> books) {
        this.bigCategory = bigCategory;
        this.smallCategory = smallCategory;
        this.books = books;
    }
}
