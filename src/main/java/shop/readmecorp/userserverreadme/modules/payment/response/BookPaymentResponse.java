package shop.readmecorp.userserverreadme.modules.payment.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.modules.book.dto.BookDTO;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BookPaymentResponse {
    private Integer id;

    private List<BookDTO> bookList;

}
