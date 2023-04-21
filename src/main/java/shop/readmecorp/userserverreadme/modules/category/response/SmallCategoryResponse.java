package shop.readmecorp.userserverreadme.modules.category.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.modules.category.dto.BigCategoryDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SmallCategoryResponse {

    private Integer id;

    private String smallCategory;

    private BigCategoryDTO bigCategory;

    private String status;

}
