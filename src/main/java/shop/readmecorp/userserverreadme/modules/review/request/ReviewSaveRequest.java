package shop.readmecorp.userserverreadme.modules.review.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.modules.book.dto.BookDTO;
import shop.readmecorp.userserverreadme.modules.user.dto.UserDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewSaveRequest {

    @NotNull(message = "도서 정보가 없습니다.")
    private Integer bookId;

    @NotNull(message = "리뷰 점수가 없습니다.")
    private Double stars;

    @NotBlank(message = "내용이 없습니다.")
    private String content;

}
