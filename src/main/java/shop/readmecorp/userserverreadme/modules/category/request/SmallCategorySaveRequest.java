package shop.readmecorp.userserverreadme.modules.category.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SmallCategorySaveRequest {

    @NotNull(message = "소분류 카테고리를 입력해주세요.")
    private String smallCategory;

}
