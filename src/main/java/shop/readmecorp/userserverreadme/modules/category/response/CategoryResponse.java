package shop.readmecorp.userserverreadme.modules.category.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {

    private Integer id;

    private String bigCategory;

    private String smallCategory;

    private String status;

}
