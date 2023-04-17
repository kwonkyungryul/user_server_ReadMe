package shop.readmecorp.userserverreadme.modules.payment.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import shop.readmecorp.userserverreadme.common.jpa.BaseTime;
import shop.readmecorp.userserverreadme.modules.book.entity.Book;
import shop.readmecorp.userserverreadme.modules.card.entity.Card;
import shop.readmecorp.userserverreadme.modules.payment.dto.BookPaymentDTO;
import shop.readmecorp.userserverreadme.modules.payment.enums.PaymentStatus;
import shop.readmecorp.userserverreadme.modules.payment.response.BookPaymentResponse;
import shop.readmecorp.userserverreadme.modules.user.entity.User;

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

    @Comment("구매한 유저")
    @ManyToOne
    private User user;

    @Comment("구매한 책")
    @ManyToOne
    private Book book;

    @Comment("총 금액")
    private Integer price;

    @Comment("결제한 카드")
    @ManyToOne
    private Card card;

    @Comment("구매한 시간")
    private LocalDateTime paymentTime;

    @Comment("책 구매내역 활성화 상태")
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Builder
    public BookPayment(Integer id, User user, Book book, Integer price,Card card,LocalDateTime paymentTime, PaymentStatus status) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.price = price;
        this.card = card;
        this.paymentTime = paymentTime;
        this.status = status;
    }

    public BookPaymentDTO toDTO() {
        return new BookPaymentDTO(id, user.toDTO(), book.toDTO(), price, card, paymentTime.toString(), status.name());
    }

    public BookPaymentResponse toResponse() {
        return new BookPaymentResponse(id, user.toDTO(), book.toDTO(), price, card, paymentTime.toString(), status.name());
    }
}

