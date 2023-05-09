package shop.readmecorp.userserverreadme.modules.cart.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.readmecorp.userserverreadme.modules.book.BookConst;
import shop.readmecorp.userserverreadme.modules.book.dto.BookDTO;
import shop.readmecorp.userserverreadme.modules.book.entity.Book;
import shop.readmecorp.userserverreadme.modules.book.entity.Heart;
import shop.readmecorp.userserverreadme.modules.book.enums.HeartStatus;
import shop.readmecorp.userserverreadme.modules.book.repository.HeartRepository;
import shop.readmecorp.userserverreadme.modules.cart.dto.CartDTO;
import shop.readmecorp.userserverreadme.modules.cart.entity.Cart;
import shop.readmecorp.userserverreadme.modules.cart.enums.CartStatus;
import shop.readmecorp.userserverreadme.modules.cart.repository.CartRepository;
import shop.readmecorp.userserverreadme.modules.cart.response.CartResponse;
import shop.readmecorp.userserverreadme.modules.category.entity.BigCategory;
import shop.readmecorp.userserverreadme.modules.file.entity.File;
import shop.readmecorp.userserverreadme.modules.file.repository.FileRepository;
import shop.readmecorp.userserverreadme.modules.review.repository.ReviewRepository;
import shop.readmecorp.userserverreadme.modules.user.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class CartService {

    private final CartRepository cartRepository;
    private final HeartRepository heartRepository;
    private final ReviewRepository reviewRepository;
    private final FileRepository fileRepository;

    public CartService(CartRepository cartRepository, HeartRepository heartRepository, ReviewRepository reviewRepository, FileRepository fileRepository) {
        this.cartRepository = cartRepository;
        this.heartRepository = heartRepository;
        this.reviewRepository = reviewRepository;
        this.fileRepository = fileRepository;
    }


    public Optional<Cart> getCart(Integer id) {
        return cartRepository.findById(id);
    }

    public List<CartDTO> getCartList(User user){
        List<Heart> hearts = heartRepository.findByUserAndStatusNot(user, HeartStatus.DELETE);
        List<Cart> cartList = cartRepository.findByStatusNotAndUser(CartStatus.DELETE, user);
        return cartList.stream().map(cart -> {
            Book book = cart.getBook();
            CartDTO cartDTO = cart.toDTO();
            BookDTO bookDTO = cartDTO.getBook();
            if (hearts.stream().map(Heart::getBook)
                    .map(Book::getId)
                    .collect(Collectors.toList())
                    .contains(bookDTO.getId())) {
                bookDTO.setIsHeart(true);
            }
            Optional<Heart> optionalHeart = heartRepository.heartCount(bookDTO.getId(), user.getId());

            BigCategory bigCategory = book.getSmallCategory().getBigCategory();

            List<File> epubFiles = fileRepository.findByFileInfo_Id(book.getEpub().getId());
            List<File> coverFiles = fileRepository.findByFileInfo_Id(book.getCover().getId());
            if (epubFiles.size() == 0 && coverFiles.size() == 0) {
                bookDTO.setEpubFile(BookConst.defaultBookFileDTO);
            } else {
                bookDTO.setEpubFile(epubFiles.get(0).toDTO());
                bookDTO.setCoverFile(coverFiles.get(0).toDTO());
            }

            cartDTO.getBook().setIsHeart(false);
            if (optionalHeart.isPresent()) {
                bookDTO.setIsHeart(true);
            }
            cartDTO.getBook().setBigCategory(bigCategory.toSingleDTO());
            Double stars = reviewRepository.findAvgStars(bookDTO.getId());
            if (stars != null) {
                stars = (Math.ceil((stars * 10) / 10));
            } else {
                stars = 0.0;
            }
            cartDTO.getBook().setStars(Math.ceil((stars * 10) / 10));
            cartDTO.getBook().setEpubFile(bookDTO.getEpubFile());
            cartDTO.getBook().setCoverFile(bookDTO.getCoverFile());
            return cartDTO;
        }).collect(Collectors.toList());
    }

    public boolean isCart(Book book, User user) {
        return cartRepository.findByUserAndStatusNotAndBook(user, CartStatus.DELETE, book).isPresent();
    }

    @Transactional
    public CartResponse save(User user, Book book) {
        CartResponse cartResponse = cartRepository.save(new Cart(null, user, book, CartStatus.ACTIVE)).toResponse();
        Optional<Heart> hearts = heartRepository.findByUserAndStatusNotAndBook(user, HeartStatus.DELETE, book);
        if (hearts.isPresent()) {
            cartResponse.getBook().setIsHeart(true);
        }

        BookDTO bookDTO = cartResponse.getBook();

        Optional<Heart> optionalHeart = heartRepository.heartCount(bookDTO.getId(), user.getId());

        BigCategory bigCategory = book.getSmallCategory().getBigCategory();

        List<File> epubFiles = fileRepository.findByFileInfo_Id(book.getEpub().getId());
        List<File> coverFiles = fileRepository.findByFileInfo_Id(book.getCover().getId());
        if (epubFiles.size() == 0 && coverFiles.size() == 0) {
            bookDTO.setEpubFile(BookConst.defaultBookFileDTO);
        } else {
            bookDTO.setEpubFile(epubFiles.get(0).toDTO());
            bookDTO.setCoverFile(coverFiles.get(0).toDTO());
        }

        cartResponse.getBook().setIsHeart(false);
        if (optionalHeart.isPresent()) {
            bookDTO.setIsHeart(true);
        }
        cartResponse.getBook().setBigCategory(bigCategory.toSingleDTO());
        Double stars = reviewRepository.findAvgStars(bookDTO.getId());
        if (stars != null) {
            stars = (Math.ceil((stars * 10) / 10));
        } else {
            stars = 0.0;
        }
        cartResponse.getBook().setStars(Math.ceil((stars * 10) / 10));
        cartResponse.getBook().setEpubFile(bookDTO.getEpubFile());
        cartResponse.getBook().setCoverFile(bookDTO.getCoverFile());
        return cartResponse;
    }

    @Transactional
    public void delete(Integer id) {
        cartRepository.deleteById(id);
    }


}
