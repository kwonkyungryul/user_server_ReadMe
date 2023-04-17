package shop.readmecorp.userserverreadme.modules.bookdeletelist.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.modules.book.dto.BookDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDeleteListDTO {

    private Integer id;

    private BookDTO book;

    private String content;

    private String status;

}
