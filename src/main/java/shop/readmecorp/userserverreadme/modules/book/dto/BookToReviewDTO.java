package shop.readmecorp.userserverreadme.modules.book.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookToReviewDTO {
    private Integer id;

    private String title;

    private String coverUrl;

    private String author;
}
