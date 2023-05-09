package shop.readmecorp.userserverreadme.modules.review.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import shop.readmecorp.userserverreadme.common.jpa.BaseTime;
import shop.readmecorp.userserverreadme.modules.book.entity.Book;
import shop.readmecorp.userserverreadme.modules.review.dto.ReviewDTO;
import shop.readmecorp.userserverreadme.modules.review.dto.ReviewNoneBookDTO;
import shop.readmecorp.userserverreadme.modules.review.enums.ReviewStatus;
import shop.readmecorp.userserverreadme.modules.review.dto.MyReviewDTO;
import shop.readmecorp.userserverreadme.modules.user.entity.User;
import shop.readmecorp.userserverreadme.util.DateTimeConverter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

    @Comment("리뷰 활성화 상태")
    @Enumerated(EnumType.STRING)
    private ReviewStatus status;

    @Builder
    public Review(Integer id, User user, Book book,Double stars ,String content, ReviewStatus status) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.stars = stars;
        this.content = content;
        this.status = status;
    }

    public ReviewDTO toDTO() {
        return new ReviewDTO(id, book.toDTO(), stars, content, DateTimeConverter.localDateTimeToString(getCreatedDate()));
    }

    public ReviewNoneBookDTO toNoneBookDTO() {
        return new ReviewNoneBookDTO(id, user.toDTO(), stars, content, DateTimeConverter.localDateTimeToString(getCreatedDate()));
    }

    public MyReviewDTO toMyReviewDTO() {
        return new MyReviewDTO(id, book.toBookToReviewDTO(), stars, content, DateTimeConverter.localDateTimeToString(getCreatedDate()));
    }
}
