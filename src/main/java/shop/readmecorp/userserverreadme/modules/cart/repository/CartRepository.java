package shop.readmecorp.userserverreadme.modules.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shop.readmecorp.userserverreadme.modules.cart.dto.CartDTO;
import shop.readmecorp.userserverreadme.modules.cart.entity.Cart;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    @Query("select c from Cart c where c.user.id = :userId")
    List<Cart> findCartByUserId(@Param(value = "userId") int userId);
}
