package shop.readmecorp.userserverreadme.modules.book.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import shop.readmecorp.userserverreadme.modules.book.entity.Book;
import shop.readmecorp.userserverreadme.modules.book.enums.BookStatus;
import shop.readmecorp.userserverreadme.modules.category.dto.CategoryDTO;
import shop.readmecorp.userserverreadme.modules.file.dto.FileInfoDTO;
import shop.readmecorp.userserverreadme.modules.publisher.dto.PublisherDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class BookSaveRequest {

    @Valid
    private PublisherDTO publisher;

    @NotBlank(message = "책 제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "작가 정보를 입력해주세요.")
    private String author;

    @NotNull(message = "책 가격을 입력해주세요.")
    private Integer price;

    @NotBlank(message = "책 소개를 입력해주세요.")
    private String introduction;

    @NotBlank(message = "책 내용을 입력해주세요.")
    private String content;

    @Valid
    private CategoryDTO category;

    @NotBlank(message = "작가 소개를 입력해주세요.")
    private String authorInfo;

    private List<MultipartFile> files;

    @Builder
    public BookSaveRequest(PublisherDTO publisher, String title, String author, Integer price, String introduction, String content, CategoryDTO category, String authorInfo, List<MultipartFile> files) {
        this.publisher = publisher;
        this.title = title;
        this.author = author;
        this.price = price;
        this.introduction = introduction;
        this.content = content;
        this.category = category;
        this.authorInfo = authorInfo;
        this.files = files;
    }

    public Book toEntity() {
        return Book.builder()
                .id(1)
                .publisher(publisher.toEntity())
                .title("덕혜옹주")
                .author("권비영")
                .price(12420)
                .introduction("책 소개글")
                .content("책 내용")
                .category(category.toEntity())
                .authorInfo("저자 소개")
                .fileInfo(null)
                .status(BookStatus.WAIT)
                .build();
    }
}
