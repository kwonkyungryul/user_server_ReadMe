package shop.readmecorp.userserverreadme.modules.book.response;

import lombok.Builder;
import lombok.Getter;
import shop.readmecorp.userserverreadme.modules.category.dto.BigCategoryDTO;
import shop.readmecorp.userserverreadme.modules.category.dto.SmallCategoryDTO;
import shop.readmecorp.userserverreadme.modules.file.dto.FileDTO;
import shop.readmecorp.userserverreadme.modules.file.dto.FileInfoDTO;
import shop.readmecorp.userserverreadme.modules.publisher.dto.PublisherDTO;
import shop.readmecorp.userserverreadme.modules.review.dto.ReviewDTO;

import java.util.List;

@Getter
public class BookDetailResponse {
    private Integer id;

    private PublisherDTO publisher;

    private String title;

    private String author;

    private Integer price;

    private String introduction;

    private BigCategoryDTO bigCategory;

    private SmallCategoryDTO smallCategory;

    private String authorInfo;

    private FileDTO epubFile;

    private FileDTO coverFile;

    private List<ReviewDTO> reviews;

    @Builder
    public BookDetailResponse(Integer id, PublisherDTO publisher, String title, String author, Integer price, String introduction, BigCategoryDTO bigCategory, SmallCategoryDTO smallCategory, String authorInfo, FileDTO epubFile, FileDTO coverFile, List<ReviewDTO> reviews) {
        this.id = id;
        this.publisher = publisher;
        this.title = title;
        this.author = author;
        this.price = price;
        this.introduction = introduction;
        this.bigCategory = bigCategory;
        this.smallCategory = smallCategory;
        this.authorInfo = authorInfo;
        this.epubFile = epubFile;
        this.coverFile = coverFile;
        this.reviews = reviews;
    }
}
