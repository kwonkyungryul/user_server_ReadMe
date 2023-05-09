package shop.readmecorp.userserverreadme.modules.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.modules.book.dto.BookToReviewDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyReviewDTO {
    private Integer id;

    private BookToReviewDTO book;

    private Double stars;

    private String content;

    private String writeTime;
}
