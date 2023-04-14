package shop.readmecorp.userserverreadme.modules.cart.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import shop.readmecorp.userserverreadme.common.jpa.BaseTime;
import shop.readmecorp.userserverreadme.modules.book.entity.Book;
import shop.readmecorp.userserverreadme.modules.cart.enums.CartStatus;
import shop.readmecorp.userserverreadme.modules.user.entity.User;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CART_TB")
public class Cart extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유번호")
    private Integer id;

    @Comment("장바구니에 담은 유저")
    @ManyToOne
    private User user;

    @Comment("장바구니에 담긴 책")
    @ManyToOne
    private Book book;

    @Comment("장바구니 활성화 상태")
    @Enumerated(EnumType.STRING)
    private CartStatus status;

}
