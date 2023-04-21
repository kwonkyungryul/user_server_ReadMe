package shop.readmecorp.userserverreadme.modules.category.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import shop.readmecorp.userserverreadme.common.jpa.BaseTime;
import shop.readmecorp.userserverreadme.modules.category.dto.BigCategoryDTO;
import shop.readmecorp.userserverreadme.modules.category.dto.SmallCategoryDTO;
import shop.readmecorp.userserverreadme.modules.category.enums.BigCategoryType;
import shop.readmecorp.userserverreadme.modules.category.enums.CategoryStatus;
import shop.readmecorp.userserverreadme.modules.category.response.BigCategoryResponse;
import shop.readmecorp.userserverreadme.modules.category.response.SmallCategoryResponse;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "BIG_CATEGORY_TB")
public class BigCategory extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유번호")
    private Integer id;

    @Comment("대분류 카테고리")
    @Enumerated(EnumType.STRING)
    private BigCategoryType bigCategory;

    @Comment("카테고리 활성화 상태")
    @Enumerated(EnumType.STRING)
    private CategoryStatus status;

    @Builder
    public BigCategory(Integer id, BigCategoryType bigCategory, CategoryStatus status) {
        this.id = id;
        this.bigCategory = bigCategory;
        this.status = status;
    }

    public BigCategoryDTO toDTO() {
        return new BigCategoryDTO(id, bigCategory.name(), status.name());
    }

    public BigCategoryResponse toResponse() {
        return new BigCategoryResponse(id, bigCategory.name(), status.name());
    }
}
