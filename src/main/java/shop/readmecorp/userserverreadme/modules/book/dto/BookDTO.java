package shop.readmecorp.userserverreadme.modules.book.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.modules.book.entity.Book;
import shop.readmecorp.userserverreadme.modules.book.enums.BookStatus;
import shop.readmecorp.userserverreadme.modules.category.dto.CategoryDTO;
import shop.readmecorp.userserverreadme.modules.file.dto.FileInfoDTO;
import shop.readmecorp.userserverreadme.modules.publisher.dto.PublisherDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    private Integer id;

    private PublisherDTO publisher;

    private String title;

    private String author;

    private Integer price;

    private String introduction;

    private String content;

    private CategoryDTO category;

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
                .content(content)
                .category(category.toEntity())
                .authorInfo(authorInfo)
                .fileInfo(fileInfo.toEntity())
                .status(BookStatus.valueOf(status))
                .build();
    }

}
