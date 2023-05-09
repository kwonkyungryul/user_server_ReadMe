package shop.readmecorp.userserverreadme.modules.book.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import shop.readmecorp.userserverreadme.modules.category.dto.SingleBigCategoryDTO;
import shop.readmecorp.userserverreadme.modules.category.dto.SmallCategoryDTO;
import shop.readmecorp.userserverreadme.modules.file.dto.FileDTO;
import shop.readmecorp.userserverreadme.modules.publisher.dto.PublisherDTO;
import shop.readmecorp.userserverreadme.modules.review.dto.ReviewNoneBookDTO;

import java.util.List;

@Getter
@Setter
public class BookDetailResponse {
    private Integer id;

    private PublisherDTO publisher;

    private String title;

    private String author;

    private Integer price;

    private String introduction;

    private SingleBigCategoryDTO bigCategory;

    private SmallCategoryDTO smallCategory;

    private String authorInfo;

    private Boolean isHeart;

    private Boolean isPurchase;

    private FileDTO epubFile;

    private FileDTO coverFile;

    private Page<ReviewNoneBookDTO> reviews;

    @Builder
    public BookDetailResponse(Integer id, PublisherDTO publisher, String title, String author, Integer price, String introduction, SingleBigCategoryDTO bigCategory, SmallCategoryDTO smallCategory, String authorInfo, Boolean isHeart, Boolean isPurchase, FileDTO epubFile, FileDTO coverFile, Page<ReviewNoneBookDTO> reviews) {
        this.id = id;
        this.publisher = publisher;
        this.title = title;
        this.author = author;
        this.price = price;
        this.introduction = introduction;
        this.bigCategory = bigCategory;
        this.smallCategory = smallCategory;
        this.authorInfo = authorInfo;
        this.isHeart = isHeart;
        this.isPurchase = isPurchase;
        this.epubFile = epubFile;
        this.coverFile = coverFile;
        this.reviews = reviews;
    }
}
