package shop.readmecorp.userserverreadme.modules.book.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import shop.readmecorp.userserverreadme.common.jpa.BaseTime;
import shop.readmecorp.userserverreadme.modules.book.dto.BookmarkDTO;
import shop.readmecorp.userserverreadme.modules.book.enums.BookmarkStatus;
import shop.readmecorp.userserverreadme.modules.book.response.BookmarkResponse;
import shop.readmecorp.userserverreadme.modules.user.entity.User;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

    @Builder
    public Bookmark(Integer id, User user, Book book, String pageNum, BookmarkStatus status) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.pageNum = pageNum;
        this.status = status;
    }

    public BookmarkDTO toDTO() {
        return new BookmarkDTO(id, user.toDTO(), book.toDTO(), pageNum, status.name()  );
    }

    public BookmarkResponse toResponse() {
        return new BookmarkResponse(id, user.toDTO(), book.toDTO(), pageNum, status.name());
    }
}
