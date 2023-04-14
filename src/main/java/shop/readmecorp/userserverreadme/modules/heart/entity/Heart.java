package shop.readmecorp.userserverreadme.modules.heart.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import shop.readmecorp.userserverreadme.common.jpa.BaseTime;
import shop.readmecorp.userserverreadme.modules.book.entity.Book;
import shop.readmecorp.userserverreadme.modules.heart.enums.HeartStatus;
import shop.readmecorp.userserverreadme.modules.user.entity.User;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "HEART_TB")
public class Heart extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유번호")
    private Integer id;

    @Comment("하트 누른 유저")
    @ManyToOne
    private User user;

    @Comment("하트 눌린 책")
    @ManyToOne
    private Book book;

    @Comment("하트 활성화 상태")
    @Enumerated(EnumType.STRING)
    private HeartStatus status;

}
