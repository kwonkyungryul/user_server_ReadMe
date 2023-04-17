package shop.readmecorp.userserverreadme.modules.book.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import shop.readmecorp.userserverreadme.common.jpa.BaseTime;
import shop.readmecorp.userserverreadme.modules.book.dto.BookDTO;
import shop.readmecorp.userserverreadme.modules.book.enums.BookStatus;
import shop.readmecorp.userserverreadme.modules.book.response.BookResponse;
import shop.readmecorp.userserverreadme.modules.category.entity.Category;
import shop.readmecorp.userserverreadme.modules.file.entity.FileInfo;
import shop.readmecorp.userserverreadme.modules.publisher.entity.Publisher;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "BOOK_TB")
public class Book extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유번호")
    private Integer id;

    @Comment("출판사")
    @ManyToOne
    private Publisher publisher;

    @Comment("책 제목")
    private String title;

    @Comment("책 저자")
    private String author;

    @Comment("책 가격")
    private Integer price;

    @Comment("책 소개")
    private String introduction;

    @Comment("책 내용")
    private String content;

    @Comment("책 카테고리")
    @OneToOne
    private Category category;

    @Comment("저자 정보")
    private String authorInfo;

    @Comment("파일 출처")
    @ManyToOne
    private FileInfo fileInfo;

    @Comment("책 활성화 상태")
    @Enumerated(EnumType.STRING)
    private BookStatus status;

    @Builder
    public Book(Integer id, Publisher publisher, String title, String author, Integer price, String introduction, String content, Category category,String authorInfo,FileInfo fileInfo, BookStatus status) {
        this.id = id;
        this.publisher = publisher;
        this.title = title;
        this.author = author;
        this.price = price;
        this.introduction = introduction;
        this.content = content;
        this.category = category;
        this.authorInfo = authorInfo;
        this.fileInfo = fileInfo;
        this.status = status;
    }

    public BookDTO toDTO() {
        return new BookDTO(id, publisher.toDTO(), title, author,price, introduction, content, category.toDTO(), authorInfo, fileInfo.toDTO(), status.name() );
    }

    public BookResponse toResponse() {
        return new BookResponse(id, publisher.toDTO(), title, author,price, introduction, content, category.toDTO(), authorInfo, fileInfo.toDTO(), status.name());
    }

}
