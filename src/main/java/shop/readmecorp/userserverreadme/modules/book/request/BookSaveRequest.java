package shop.readmecorp.userserverreadme.modules.book.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import shop.readmecorp.userserverreadme.modules.book.entity.Book;
import shop.readmecorp.userserverreadme.modules.book.enums.BookStatus;
import shop.readmecorp.userserverreadme.modules.category.dto.BigCategoryDTO;
import shop.readmecorp.userserverreadme.modules.category.dto.SmallCategoryDTO;
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

    @NotNull(message = "책 파일을 업로드해주세요.")
    private MultipartFile epubFile;

    @Valid
    private BigCategoryDTO bigCategory;

    @Valid
    private SmallCategoryDTO smallCategory;

    @NotBlank(message = "작가 소개를 입력해주세요.")
    private String authorInfo;

    private List<MultipartFile> files;

    @Builder
    public BookSaveRequest(PublisherDTO publisher, String title, String author, Integer price, String introduction, MultipartFile epubFile, BigCategoryDTO bigCategory, SmallCategoryDTO smallCategory, String authorInfo, List<MultipartFile> files) {
        this.publisher = publisher;
        this.title = title;
        this.author = author;
        this.price = price;
        this.introduction = introduction;
        this.epubFile = epubFile;
        this.bigCategory = bigCategory;
        this.smallCategory = smallCategory;
        this.authorInfo = authorInfo;
        this.files = files;
    }

    public Book toEntity() {
        return Book.builder()
                .id(null)
                .publisher(publisher.toEntity())
                .title(title)
                .author(author)
                .price(price)
                .introduction(introduction)
                .smallCategory(smallCategory.toEntity())
                .authorInfo(authorInfo)
                .epub(null)
                .cover(null)
                .status(BookStatus.WAIT)
                .build();
    }
}
