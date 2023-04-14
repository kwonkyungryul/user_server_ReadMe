package shop.readmecorp.userserverreadme.modules.bookmark.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import shop.readmecorp.userserverreadme.common.jpa.BaseTime;
import shop.readmecorp.userserverreadme.modules.book.entity.Book;
import shop.readmecorp.userserverreadme.modules.bookmark.enums.BookmarkStatus;
import shop.readmecorp.userserverreadme.modules.user.entity.User;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BOOKMARK_TB")
public class Bookmark extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유번호")
    private Integer id;

    @Comment("북마크 한 유저")
    @ManyToOne
    private User user;

    @Comment("북마크 한 책")
    @ManyToOne
    private Book book;

    @Comment("북마크 한 페이지")
    private String pageNum;

    @Comment("북마크 활성화 상태")
    @Enumerated(EnumType.STRING)
    private BookmarkStatus status;

}
