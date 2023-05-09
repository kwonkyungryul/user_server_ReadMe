package shop.readmecorp.userserverreadme.modules.book.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.modules.file.dto.FileDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookToPaymentDTO {
    private Integer id;

    private String title;

    private String publisherName;

    private Integer price;

    private FileDTO coverFile;
}
