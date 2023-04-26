package shop.readmecorp.userserverreadme.modules.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.modules.book.entity.Book;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {

    private Integer id;

    private String title;

    private String businessName;

    private String author;

    private Integer price;

    private String status;

    public void BookDTO(Book book) {
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.businessName = book.getPublisher().getBusinessName();
        this.price = book.getPrice();
    }
}