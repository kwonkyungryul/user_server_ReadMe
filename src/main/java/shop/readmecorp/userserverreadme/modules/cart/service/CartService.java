package shop.readmecorp.userserverreadme.modules.cart.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.readmecorp.userserverreadme.common.exception.Exception400;
import shop.readmecorp.userserverreadme.modules.book.BookConst;
import shop.readmecorp.userserverreadme.modules.book.dto.BookDTO;
import shop.readmecorp.userserverreadme.modules.book.entity.Book;
import shop.readmecorp.userserverreadme.modules.book.repository.BookRepository;
import shop.readmecorp.userserverreadme.modules.cart.CartConst;
import shop.readmecorp.userserverreadme.modules.cart.dto.CartDTO;
import shop.readmecorp.userserverreadme.modules.cart.entity.Cart;
import shop.readmecorp.userserverreadme.modules.cart.enums.CartStatus;
import shop.readmecorp.userserverreadme.modules.cart.repository.CartRepository;
import shop.readmecorp.userserverreadme.modules.cart.request.CartSaveRequest;
import shop.readmecorp.userserverreadme.modules.cart.response.CartResponse;
import shop.readmecorp.userserverreadme.modules.file.entity.File;
import shop.readmecorp.userserverreadme.modules.file.repository.FileRepository;
import shop.readmecorp.userserverreadme.modules.review.repository.ReviewRepository;
import shop.readmecorp.userserverreadme.modules.user.UserConst;
import shop.readmecorp.userserverreadme.modules.user.entity.User;
import shop.readmecorp.userserverreadme.modules.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final FileRepository fileRepository;
    private final ReviewRepository reviewRepository;

    public CartService(CartRepository cartRepository, UserRepository userRepository, BookRepository bookRepository, FileRepository fileRepository, ReviewRepository reviewRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.fileRepository = fileRepository;
        this.reviewRepository = reviewRepository;
    }

    public Page<Cart> getPage(Pageable pageable) {
        return cartRepository.findAll(pageable);
    }


    public Optional<Cart> getCart(Integer id) {
        return cartRepository.findById(id);
    }

    public List<CartDTO> getCartByUserId(Integer userId){
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()){
            throw new Exception400(UserConst.notFound);
        }

        User user = optionalUser.get();
        List<Cart> cartList = cartRepository.findCartByUserId(user.getId());

        List<CartDTO> cartDTOS = cartList.stream().map(Cart::toDTO).collect(Collectors.toList());

        for (int i = 0; i < cartDTOS.size(); i++) {
//            File epubFiles = fileRepository.findByFileInfo_Id(cartList.get(i).getBook().getEpub().getId());
//            File coverFiles = fileRepository.findByFileInfo_Id(cartList.get(i).getBook().getCover().getId());
//            Double stars = reviewRepository.findAvgStars(cartList.get(i).getId());
//            cartDTOS.get(i).getBook().setEpubFile(epubFiles.toDTO());
//            cartDTOS.get(i).getBook().setCoverFile(coverFiles.toDTO());
//            cartDTOS.get(i).getBook().setStars(stars);

            // TODO 로그인 시 좋아요 체크(isHeart) 해야함. 아래 것들도 마찬가지
            cartDTOS.get(i).getBook().setIsHeart(true);

//            if (stars != null) {
//                cartDTOS.get(i).getBook().setStars(Math.ceil((stars * 10) / 10));
//            } else {
//                cartDTOS.get(i).getBook().setStars(0.0);
//            }
        }

        return cartDTOS;
    }

    @Transactional
    public CartResponse save(CartSaveRequest request) {
        Optional<User> optionalUser = userRepository.findById(request.getUserId());
        Optional<Book> optionalBook = bookRepository.findById(request.getBookId());

        if (optionalUser.isEmpty()){
            throw new Exception400(UserConst.notFound);
        }

        if (optionalBook.isEmpty()){
            throw new Exception400(BookConst.notFound);
        }

        Book book = optionalBook.get();

//        BookDTO bookDTO = book.toDTO();
//        File epubFiles = fileRepository.findByFileInfo_Id(book.getEpub().getId());
//        File coverFiles = fileRepository.findByFileInfo_Id(book.getCover().getId());
//        Double stars = reviewRepository.findAvgStars(bookDTO.getId());
//        bookDTO.setEpubFile(epubFiles.toDTO());
//        bookDTO.setCoverFile(coverFiles.toDTO());
//
//        // TODO 로그인 시 좋아요 체크(isHeart) 해야함. 아래 것들도 마찬가지
//        bookDTO.setIsHeart(true);
//        if (stars != null) {
//            bookDTO.setStars(Math.ceil((stars * 10) / 10));
//        } else {
//            bookDTO.setStars(0.0);
//        }
//
//        Cart cart = Cart.builder()
//                .user(optionalUser.get())
//                .book(optionalBook.get())
//                .status(CartStatus.ACTIVE)
//                .build();
//        CartResponse response = cartRepository.save(cart).toResponse();
//
//        response.setBook(bookDTO);

        return null;
    }

//    @Transactional
//    public Cart update(@Valid CartUpdateRequest request, Cart cart) {
//            cart.setUser(request.getUser().toEntity());
//            cart.setBook(request.getBook().toEntity());
//            cart.setStatus(CartStatus.valueOf(request.getStatus()));
//        return cartRepository.save(cart);
//    }

    @Transactional
    public void delete(Integer id) {
        Optional<Cart> optionalCart = cartRepository.findById(id);
        if (optionalCart.isEmpty()) {
            throw new Exception400(CartConst.notFound);
        }
        Cart cart = optionalCart.get();

        try {
            cartRepository.delete(cart);
        } catch (Exception e) {
            throw new Exception400(CartConst.internalServerError);
        }
    }
}
