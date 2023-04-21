package shop.readmecorp.userserverreadme.modules.book.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.modules.book.entity.Book;
import shop.readmecorp.userserverreadme.modules.book.enums.BookStatus;
import shop.readmecorp.userserverreadme.modules.category.dto.BigCategoryDTO;
import shop.readmecorp.userserverreadme.modules.category.dto.SmallCategoryDTO;
import shop.readmecorp.userserverreadme.modules.category.entity.BigCategory;
import shop.readmecorp.userserverreadme.modules.file.dto.FileInfoDTO;
import shop.readmecorp.userserverreadme.modules.publisher.dto.PublisherDTO;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    @NotNull(message = "책 ID가 없습니다.")
    private Integer id;

    private PublisherDTO publisher;

    private String title;

    private String author;

    private Integer price;

    private String introduction;

    private String filePath;

    private BigCategoryDTO bigCategory;

    private SmallCategoryDTO smallCategory;

    private String authorInfo;

    private FileInfoDTO fileInfo;

    private String status;

    public Book toEntity() {
        return Book.builder()
                .id(id)
                .publisher(publisher.toEntity())
                .title(title)
                .author(author)
                .price(price)
                .introduction(introduction)
                .filePath(filePath)
                .bigCategory(bigCategory.toEntity())
                .smallCategory(smallCategory.toEntity())
                .authorInfo(authorInfo)
                .fileInfo(fileInfo.toEntity())
                .status(BookStatus.valueOf(status))
                .build();
    }

}
