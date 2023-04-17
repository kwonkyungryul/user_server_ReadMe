package shop.readmecorp.userserverreadme.modules.bookdeletelist.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import shop.readmecorp.userserverreadme.common.jpa.BaseTime;
import shop.readmecorp.userserverreadme.modules.book.entity.Book;
import shop.readmecorp.userserverreadme.modules.bookdeletelist.dto.BookDeleteListDTO;
import shop.readmecorp.userserverreadme.modules.bookdeletelist.enums.BookDeleteStatus;
import shop.readmecorp.userserverreadme.modules.bookdeletelist.response.BookDeleteListResponse;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "BOOK_DELETE_LIST_TB")
public class BookDeleteList extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유번호")
    private Integer id;

    @Comment("삭제요청한 책")
    @ManyToOne
    private Book book;

    @Comment("요청사항")
    private String content;

    @Comment("삭제요청 활성화 상태")
    @Enumerated(EnumType.STRING)
    private BookDeleteStatus status;

    @Builder
    public BookDeleteList(Integer id, Book book, String content, BookDeleteStatus status) {
        this.id = id;
        this.book = book;
        this.content = content;
        this.status = status;
    }

    public BookDeleteListDTO toDTO() {
        return new BookDeleteListDTO(id, book.toDTO(), content, status.name()  );
    }

    public BookDeleteListResponse toResponse() {
        return new BookDeleteListResponse(id, book.toDTO(), content, status.name());
    }
}
