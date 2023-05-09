package shop.readmecorp.userserverreadme.modules.book.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.modules.category.dto.BigCategoryDTO;
import shop.readmecorp.userserverreadme.modules.category.dto.SmallCategoryDTO;
import shop.readmecorp.userserverreadme.modules.file.dto.FileInfoDTO;
import shop.readmecorp.userserverreadme.modules.publisher.dto.PublisherDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {

    private Integer id;

    private PublisherDTO publisher;

    private String title;

    private String author;

    private Integer price;

    private String introduction;

    private BigCategoryDTO bigCategory;

    private SmallCategoryDTO smallCategory;

    private String authorInfo;

    private FileInfoDTO epubFile;

    private FileInfoDTO coverFile;

}
