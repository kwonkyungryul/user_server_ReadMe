package shop.readmecorp.userserverreadme.modules.category.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import shop.readmecorp.userserverreadme.common.jpa.BaseTime;
import shop.readmecorp.userserverreadme.modules.category.enums.BigCategoryType;
import shop.readmecorp.userserverreadme.modules.category.enums.CategoryStatus;
import shop.readmecorp.userserverreadme.modules.category.enums.SmallCategoryType;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
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

}
