package shop.readmecorp.userserverreadme.modules.book.request;

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
public class BookmarkSaveRequest {

    @NotNull(message = "북정보가 존재하지 않습니다.")
    private Integer bookId;

    @NotBlank(message = "페이지 정보가 존재하지 않습니다.")
    private String pageNum;

}
