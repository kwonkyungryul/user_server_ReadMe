package shop.readmecorp.userserverreadme.modules.bookupdatelist.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import shop.readmecorp.userserverreadme.common.jpa.BaseTime;
import shop.readmecorp.userserverreadme.modules.book.entity.Book;
import shop.readmecorp.userserverreadme.modules.bookupdatelist.dto.BookUpdateListDTO;
import shop.readmecorp.userserverreadme.modules.bookupdatelist.enums.BookUpdateListStatus;
import shop.readmecorp.userserverreadme.modules.bookupdatelist.response.BookUpdateListResponse;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "BOOK_UPDATE_LIST_TB")
public class BookUpdateList extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유번호")
    private Integer id;

    @Comment("수정요청한 책")
    @ManyToOne
    private Book book;

    @Comment("요청사항")
    private String content;

    @Comment("수정요청 활성화 상태")
    @Enumerated(EnumType.STRING)
    private BookUpdateListStatus status;

    @Builder
    public BookUpdateList(Integer id, Book book, String content, BookUpdateListStatus status) {
        this.id = id;
        this.book = book;
        this.content = content;
        this.status = status;
    }

    public BookUpdateListDTO toDTO() {
        return new BookUpdateListDTO(id, book.toDTO(), content, status.name()  );
    }

    public BookUpdateListResponse toResponse() {
        return new BookUpdateListResponse(id, book.toDTO(), content, status.name());
    }
}
