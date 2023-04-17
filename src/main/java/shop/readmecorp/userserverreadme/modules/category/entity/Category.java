package shop.readmecorp.userserverreadme.modules.category.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import shop.readmecorp.userserverreadme.common.jpa.BaseTime;
import shop.readmecorp.userserverreadme.modules.category.dto.CategoryDTO;
import shop.readmecorp.userserverreadme.modules.category.enums.BigCategoryType;
import shop.readmecorp.userserverreadme.modules.category.enums.CategoryStatus;
import shop.readmecorp.userserverreadme.modules.category.enums.SmallCategoryType;
import shop.readmecorp.userserverreadme.modules.category.response.CategoryResponse;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "CATEGORY_TB")
public class Category extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유번호")
    private Integer id;

    @Comment("큰 카테고리")
    @Enumerated(EnumType.STRING)
    private BigCategoryType bigCategory;

    @Comment("작은 카테고리")
    @Enumerated(EnumType.STRING)
    private SmallCategoryType smallCategory;

    @Comment("카테고리 활성화 상태")
    @Enumerated(EnumType.STRING)
    private CategoryStatus status;


    @Builder
    public Category(Integer id, BigCategoryType bigCategory, SmallCategoryType smallCategory, CategoryStatus status) {
        this.id = id;
        this.bigCategory = bigCategory;
        this.smallCategory = smallCategory;
        this.status = status;
    }

    public CategoryDTO toDTO() {
        return new CategoryDTO(id, bigCategory.name(), smallCategory.name(), status.name() );
    }

    public CategoryResponse toResponse() {
        return new CategoryResponse(id, bigCategory.name(), smallCategory.name(), status.name());
    }
}
