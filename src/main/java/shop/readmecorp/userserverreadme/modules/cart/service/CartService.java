package shop.readmecorp.userserverreadme.modules.cart.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.readmecorp.userserverreadme.modules.cart.entity.Cart;
import shop.readmecorp.userserverreadme.modules.cart.enums.CartStatus;
import shop.readmecorp.userserverreadme.modules.cart.repository.CartRepository;
import shop.readmecorp.userserverreadme.modules.cart.request.CartSaveRequest;
import shop.readmecorp.userserverreadme.modules.cart.request.CartUpdateRequest;

import javax.validation.Valid;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Page<Cart> getPage(Pageable pageable) {
        return cartRepository.findAll(pageable);
    }


    public Optional<Cart> getCart(Integer id) {
        return null;
    }

    @Transactional
    public Cart save(CartSaveRequest request) {

        return cartRepository.save(request.toEntity());
    }

    @Transactional
    public Cart update(@Valid CartUpdateRequest request, Cart cart) {
            cart.setUser(request.getUser().toEntity());
            cart.setBook(request.getBook().toEntity());
            cart.setStatus(CartStatus.valueOf(request.getStatus()));
        return cartRepository.save(cart);
    }

    @Transactional
    public void delete(Cart cart) {
    }
}
