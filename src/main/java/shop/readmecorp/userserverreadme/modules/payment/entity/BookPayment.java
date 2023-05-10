package shop.readmecorp.userserverreadme.modules.payment.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import shop.readmecorp.userserverreadme.modules.common.jpa.BaseTime;
import shop.readmecorp.userserverreadme.modules.book.entity.Book;
import shop.readmecorp.userserverreadme.modules.payment.dto.BookPaymentDTO;
import shop.readmecorp.userserverreadme.modules.payment.enums.PaymentStatus;
import shop.readmecorp.userserverreadme.modules.payment.enums.PaymentType;
import shop.readmecorp.userserverreadme.modules.user.entity.User;
import shop.readmecorp.userserverreadme.util.DateTimeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "BOOK_PAYMENT_TB")
public class BookPayment extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유번호")
    private Integer id;

    @Comment("결제 번호")
    private Integer paymentNo;

    @Comment("구매한 유저")
    @ManyToOne
    private User user;

    @Comment("구매한 책")
    @ManyToOne
    private Book book;

    @Comment("총 금액")
    private Integer price;

    @Comment("구매한 시간")
    private LocalDateTime paymentTime;

    @Comment("결제 타입")
    @Enumerated(EnumType.STRING)
    private PaymentType type;

    @Comment("책 구매내역 활성화 상태")
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Builder
    public BookPayment(Integer id, Integer paymentNo, User user, Book book, Integer price, LocalDateTime paymentTime, PaymentType type, PaymentStatus status) {
        this.id = id;
        this.paymentNo = paymentNo;
        this.user = user;
        this.book = book;
        this.price = price;
        this.paymentTime = paymentTime;
        this.type = type;
        this.status = status;
    }

    public BookPaymentDTO toDTO() {
        return new BookPaymentDTO(id, book.toBookToPaymentDTO(), price, DateTimeConverter.localDateTimeToString(paymentTime));
    }

}

