package shop.readmecorp.userserverreadme.modules.category.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import shop.readmecorp.userserverreadme.modules.common.jpa.BaseTime;
import shop.readmecorp.userserverreadme.modules.category.dto.SmallCategoryDTO;
import shop.readmecorp.userserverreadme.modules.category.enums.CategoryStatus;
import shop.readmecorp.userserverreadme.modules.category.enums.SmallCategoryType;
import shop.readmecorp.userserverreadme.modules.category.response.SmallCategoryResponse;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "SMALL_CATEGORY_TB")
public class SmallCategory extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유번호")
    private Integer id;

    @Comment("소분류 카테고리")
    @Enumerated(EnumType.STRING)
    private SmallCategoryType smallCategory;

    @Comment("대분류 카테고리")
    @ManyToOne
    private BigCategory bigCategory;

    @Comment("카테고리 활성화 상태")
    @Enumerated(EnumType.STRING)
    private CategoryStatus status;

    @Builder
    public SmallCategory(Integer id, SmallCategoryType smallCategory, BigCategory bigCategory, CategoryStatus status) {
        this.id = id;
        this.smallCategory = smallCategory;
        this.bigCategory = bigCategory;
        this.status = status;
    }

    public SmallCategoryDTO toDTO() {
        return new SmallCategoryDTO(id, smallCategory.name());
    }

    public SmallCategoryResponse toResponse() {
        return new SmallCategoryResponse(id, smallCategory.name(), bigCategory.toDTO(), status.name());
    }
}
