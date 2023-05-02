package shop.readmecorp.userserverreadme.modules.cart.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.readmecorp.userserverreadme.common.exception.Exception400;
import shop.readmecorp.userserverreadme.modules.book.BookConst;
import shop.readmecorp.userserverreadme.modules.book.entity.Book;
import shop.readmecorp.userserverreadme.modules.book.repository.BookRepository;
import shop.readmecorp.userserverreadme.modules.cart.dto.CartDTO;
import shop.readmecorp.userserverreadme.modules.cart.entity.Cart;
import shop.readmecorp.userserverreadme.modules.cart.enums.CartStatus;
import shop.readmecorp.userserverreadme.modules.cart.repository.CartRepository;
import shop.readmecorp.userserverreadme.modules.cart.request.CartSaveRequest;
import shop.readmecorp.userserverreadme.modules.cart.request.CartUpdateRequest;
import shop.readmecorp.userserverreadme.modules.user.UserConst;
import shop.readmecorp.userserverreadme.modules.user.entity.User;
import shop.readmecorp.userserverreadme.modules.user.repository.UserRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public CartService(CartRepository cartRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public Page<Cart> getPage(Pageable pageable) {
        return cartRepository.findAll(pageable);
    }


    public Optional<Cart> getCart(Integer id) {
        return cartRepository.findById(id);
    }

    public List<Cart> getCartByUserId(Integer userId){
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()){
            throw new Exception400(UserConst.notFound);
        }

        return cartRepository.findCartByUserId(userId);
    }

    @Transactional
    public Cart save(CartSaveRequest request) {
        Optional<User> optionalUser = userRepository.findById(request.getUserId());
        Optional<Book> optionalBook = bookRepository.findById(request.getBookId());

        if (optionalUser.isEmpty()){
            throw new Exception400(UserConst.notFound);
        }

        if (optionalBook.isEmpty()){
            throw new Exception400(BookConst.notFound);
        }

        Cart cart = Cart.builder()
                .user(optionalUser.get())
                .book(optionalBook.get())
                .status(CartStatus.ACTIVE)
                .build();


        return cartRepository.save(cart);
    }

//    @Transactional
//    public Cart update(@Valid CartUpdateRequest request, Cart cart) {
//            cart.setUser(request.getUser().toEntity());
//            cart.setBook(request.getBook().toEntity());
//            cart.setStatus(CartStatus.valueOf(request.getStatus()));
//        return cartRepository.save(cart);
//    }

    @Transactional
    public void delete(Cart cart) {

        cartRepository.delete(cart);
    }
}
