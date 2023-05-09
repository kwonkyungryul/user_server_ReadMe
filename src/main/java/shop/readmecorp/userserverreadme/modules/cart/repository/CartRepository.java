package shop.readmecorp.userserverreadme.modules.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shop.readmecorp.userserverreadme.modules.book.entity.Book;
import shop.readmecorp.userserverreadme.modules.book.entity.Heart;
import shop.readmecorp.userserverreadme.modules.book.enums.HeartStatus;
import shop.readmecorp.userserverreadme.modules.cart.dto.CartDTO;
import shop.readmecorp.userserverreadme.modules.cart.entity.Cart;
import shop.readmecorp.userserverreadme.modules.cart.enums.CartStatus;
import shop.readmecorp.userserverreadme.modules.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    @Query("select c from Cart c where c.user.id = :userId")
    List<Cart> findCartByUserId(@Param(value = "userId") int userId);

    List<Cart> findByStatusNotAndUser(CartStatus status, User user);
    Optional<Cart> findByUserAndStatusNotAndBook(User user, CartStatus status, Book book);

}
