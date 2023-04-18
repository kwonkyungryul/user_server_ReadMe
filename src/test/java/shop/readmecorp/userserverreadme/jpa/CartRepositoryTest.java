package shop.readmecorp.userserverreadme.jpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import shop.readmecorp.userserverreadme.common.jpa.RoleType;
import shop.readmecorp.userserverreadme.modules.book.entity.Book;
import shop.readmecorp.userserverreadme.modules.book.enums.BookStatus;
import shop.readmecorp.userserverreadme.modules.cart.entity.Cart;
import shop.readmecorp.userserverreadme.modules.cart.enums.CartStatus;
import shop.readmecorp.userserverreadme.modules.cart.repository.CartRepository;
import shop.readmecorp.userserverreadme.modules.category.entity.Category;
import shop.readmecorp.userserverreadme.modules.category.enums.BigCategoryType;
import shop.readmecorp.userserverreadme.modules.category.enums.CategoryStatus;
import shop.readmecorp.userserverreadme.modules.category.enums.SmallCategoryType;
import shop.readmecorp.userserverreadme.modules.file.entity.FileInfo;
import shop.readmecorp.userserverreadme.modules.file.enums.FileType;
import shop.readmecorp.userserverreadme.modules.publisher.entity.Publisher;
import shop.readmecorp.userserverreadme.modules.publisher.enums.PublisherStatus;
import shop.readmecorp.userserverreadme.modules.user.entity.User;
import shop.readmecorp.userserverreadme.modules.user.enums.UserStatus;

import java.time.LocalDateTime;
import java.util.Optional;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@DisplayName("CART JPA 테스트")
public class CartRepositoryTest {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @Transactional
    void selectAll() {
        var Carts = cartRepository.findAll();
        Assertions.assertNotEquals(Carts.size(), 0);

        Cart cart = Carts.get(0);
        Assertions.assertEquals(cart.getBook().getTitle(), "책제목1");
    }

    @Test
    @Transactional
    void selectAndUpdate() {
        var optionalCarts = this.cartRepository.findById(1);

        if(optionalCarts.isPresent()) {
            var result = optionalCarts.get();
            Assertions.assertEquals(result.getBook().getTitle(), "책제목1");

            var status = CartStatus.WAIT;
            result.setStatus(status);
            Cart merge = entityManager.merge(result);

            Assertions.assertEquals(merge.getStatus(), CartStatus.WAIT);
        } else {
            Assertions.assertNotNull(optionalCarts.get());
        }
    }

    @Test
    @Transactional
    void insertAndDelete() {
        Cart cart = setUp(CartStatus.ACTIVE);
        Optional<Cart> findCart = this.cartRepository.findById(cart.getId());

        if(findCart.isPresent()) {
            var result = findCart.get();
            Assertions.assertEquals(result.getBook().getTitle(), "책제목");
            entityManager.remove(cart);
            Optional<Cart> deleteCart = this.cartRepository.findById(cart.getId());
            if (deleteCart.isPresent()) {
                Assertions.assertNull(deleteCart.get());
            }
        } else {
            Assertions.assertNotNull(findCart.get());
        }
    }

    private Cart setUp(CartStatus status){
        FileInfo fileInfo = FileInfo.builder().type(FileType.BOOK).build();
        User user = User.builder().username("유저이름").password("1234").role(RoleType.USER).isMembership(true).isAutoPayment(true).joinTime(LocalDateTime.now()).fileInfo(fileInfo).status(UserStatus.ACTIVE).build();
        Publisher publisher =Publisher.builder().username("출판사이름").password("1234").role(RoleType.PUBLISHER).businessNumber("1234").businessName("사업자이름").joinTime(LocalDateTime.now()).status(PublisherStatus.ACTIVE).build();
        Category category = Category.builder().bigCategory(BigCategoryType.경영).smallCategory(SmallCategoryType.경영일반).status(CategoryStatus.ACTIVE).build();
        Book book = Book.builder().publisher(publisher).title("책제목").author("저자이름").price(1000).introduction("책소개").content("책내용").category(category).fileInfo(fileInfo).status(BookStatus.ACTIVE).build();

        var cart = new Cart();
        cart.setUser(user);
        cart.setBook(book);
        cart.setStatus(status);

        return this.entityManager.persist(cart);
    }
}
