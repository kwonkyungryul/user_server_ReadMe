package shop.readmecorp.userserverreadme.modules.bookupdatelist.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.modules.book.dto.BookDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookUpdateListSaveRequest {

    private BookDTO book;

    private String content;

}
