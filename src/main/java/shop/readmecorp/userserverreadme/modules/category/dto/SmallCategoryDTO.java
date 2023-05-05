package shop.readmecorp.userserverreadme.modules.category.dto;

import lombok.*;
import shop.readmecorp.userserverreadme.modules.category.entity.SmallCategory;
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
    private String name;

    @Builder
    public SmallCategoryDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public SmallCategory toEntity() {
        return SmallCategory.builder()
                .id(id)
                .smallCategory(SmallCategoryType.valueOf(name))
                .build();
    }

}