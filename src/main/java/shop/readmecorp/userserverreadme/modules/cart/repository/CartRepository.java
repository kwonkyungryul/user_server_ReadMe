package shop.readmecorp.userserverreadme.modules.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.readmecorp.userserverreadme.modules.cart.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
