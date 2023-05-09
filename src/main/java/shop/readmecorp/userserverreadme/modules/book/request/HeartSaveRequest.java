package shop.readmecorp.userserverreadme.modules.book.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.modules.book.dto.BookDTO;
import shop.readmecorp.userserverreadme.modules.user.dto.UserDTO;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HeartSaveRequest {
    @NotNull(message = "도서 정보가 없습니다.")
    private Integer bookId;
    private Boolean check;

}
