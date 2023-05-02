package shop.readmecorp.userserverreadme.modules.book.dto;

import lombok.*;
import shop.readmecorp.userserverreadme.modules.book.entity.Book;
import shop.readmecorp.userserverreadme.modules.book.enums.BookStatus;
import shop.readmecorp.userserverreadme.modules.category.dto.BigCategoryDTO;
import shop.readmecorp.userserverreadme.modules.category.dto.SmallCategoryDTO;
import shop.readmecorp.userserverreadme.modules.category.entity.BigCategory;
import shop.readmecorp.userserverreadme.modules.file.dto.FileDTO;
import shop.readmecorp.userserverreadme.modules.file.dto.FileInfoDTO;
import shop.readmecorp.userserverreadme.modules.publisher.dto.PublisherDTO;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BookDTO {
    @NotNull(message = "책 ID가 없습니다.")
    private Integer id;

    private PublisherDTO publisher;

    private String title;

    private String author;

    private Integer price;

    private String introduction;

    private BigCategoryDTO bigCategory;

    private SmallCategoryDTO smallCategory;

    private String authorInfo;

    private Boolean isHeart;

    private List<FileDTO> fileDTO;

    private Double stars;

    private String status;

    @Builder
    public BookDTO(Integer id, PublisherDTO publisher, String title, String author, Integer price, String introduction, BigCategoryDTO bigCategory, SmallCategoryDTO smallCategory, String authorInfo, List<FileDTO> fileDTO, String status) {
        this.id = id;
        this.publisher = publisher;
        this.title = title;
        this.author = author;
        this.price = price;
        this.introduction = introduction;
        this.bigCategory = bigCategory;
        this.smallCategory = smallCategory;
        this.authorInfo = authorInfo;
        this.fileDTO = fileDTO;
        this.status = status;
    }

    //    public Book toEntity() {
//        return Book.builder()
//                .id(id)
//                .publisher(publisher.toEntity())
//                .title(title)
//                .author(author)
//                .price(price)
//                .introduction(introduction)
//                .bigCategory(bigCategory.toEntity())
//                .smallCategory(smallCategory.toEntity())
//                .authorInfo(authorInfo)
//                .status(BookStatus.valueOf(status))
//                .build();
//    }

}
