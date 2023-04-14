package shop.readmecorp.userserverreadme.modules.review.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import shop.readmecorp.userserverreadme.common.jpa.BaseTime;
import shop.readmecorp.userserverreadme.modules.book.entity.Book;
import shop.readmecorp.userserverreadme.modules.review.enums.ReviewStatus;
import shop.readmecorp.userserverreadme.modules.user.entity.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "REVIEW_TB")
public class Review extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유번호")
    private Integer id;

    @Comment("리뷰 작성한 유저")
    @OneToOne
    private User user;

    @Comment("리뷰 작성된 책")
    @ManyToOne
    private Book book;

    @Comment("별점")
    private Double stars;

    @Comment("리뷰 내용")
    private String content;

    @Comment("작성한 시간")
    private LocalDateTime writeTime;

    @Comment("리뷰 활성화 상태")
    @Enumerated(EnumType.STRING)
    private ReviewStatus status;
}
