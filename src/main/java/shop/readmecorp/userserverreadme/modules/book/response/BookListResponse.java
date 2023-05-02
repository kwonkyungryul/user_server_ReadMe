package shop.readmecorp.userserverreadme.modules.book.response;

import lombok.Getter;
import lombok.Setter;
import shop.readmecorp.userserverreadme.modules.book.dto.BookDTO;

import java.util.List;

@Getter
@Setter
public class BookListResponse {
    private List<BookDTO> randomBook;

    private List<BookDTO> bookList;
}
