package shop.readmecorp.userserverreadme.modules.cart.service;

import org.springframework.stereotype.Service;
import shop.readmecorp.userserverreadme.modules.cart.repository.CartRepository;

@Service
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }
}
