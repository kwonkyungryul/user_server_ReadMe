package shop.readmecorp.userserverreadme.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import shop.readmecorp.userserverreadme.common.jpa.RoleType;
import shop.readmecorp.userserverreadme.modules.book.entity.Book;
import shop.readmecorp.userserverreadme.modules.book.enums.BookStatus;
import shop.readmecorp.userserverreadme.modules.cart.CartConst;
import shop.readmecorp.userserverreadme.modules.cart.controller.CartController;
import shop.readmecorp.userserverreadme.modules.cart.entity.Cart;
import shop.readmecorp.userserverreadme.modules.cart.enums.CartStatus;
import shop.readmecorp.userserverreadme.modules.cart.request.CartSaveRequest;
import shop.readmecorp.userserverreadme.modules.cart.request.CartUpdateRequest;
import shop.readmecorp.userserverreadme.modules.cart.service.CartService;
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

import javax.validation.constraints.Null;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CartController.class)
@MockBean(JpaMetamodelMappingContext.class)
public class CartMockTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private CartService cartService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("장바구니 조회 (페이지)")
    void getPage() throws Exception {
        Pageable pageable = PageRequest.of(1, 10);
        Page<Cart> page = new PageImpl<>(
                List.of(
                        new Cart(1, makeUser(), makeBook(makePublisher(), makeCategory()), CartStatus.WAIT),
                        new Cart(2, makeUser(), makeBook(makePublisher(), makeCategory()), CartStatus.ACTIVE)
                )
        );


        // given
        given(this.cartService.getPage(pageable)).willReturn(page);


        // When
        ResultActions perform = this.mvc.perform(
                get("/carts?page={page}&size={size}", 1, 10)
                        .accept(MediaType.APPLICATION_JSON)
        );


        // Then
        perform
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].status").value(CartStatus.WAIT.name()))

                .andExpect(jsonPath("$.content[1].id").value(2))
                .andExpect(jsonPath("$.content[1].status").value(CartStatus.ACTIVE.name()))
        ;
    }

    @Test
    @DisplayName("장바구니 조회 실패")
    void getCartFail() throws Exception {

        // given
        int id = 0;
        given(this.cartService.getCart(id)).willReturn(Optional.empty());


        // When
        ResultActions perform = this.mvc.perform(
                get("/carts/{id}", id)
                        .accept(MediaType.APPLICATION_JSON)
        );


        // Then
        perform
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.message").value("장바구니가 존재하지 않습니다."))
        ;
    }




    @Test
    @DisplayName("장바구니 조회")
    void getCart() throws Exception {

        // given
        int id = 0;
        given(this.cartService.getCart(id))
                .willReturn(
                        Optional.of(new Cart(1, makeUser(), makeBook(makePublisher(), makeCategory()), CartStatus.WAIT))
                );


        // When
        ResultActions perform = this.mvc.perform(
                get("/carts/{id}", id)
                        .accept(MediaType.APPLICATION_JSON)
        );


        // Then
        perform
                .andExpect(status().isOk())
                .andDo(print())

                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.status").value(CartStatus.WAIT.name()))
        ;
    }

    @Test
    @DisplayName("장바구니 저장 실패")
    void saveCartFail() throws Exception {


        // given
        CartSaveRequest request = new CartSaveRequest(makeUser().toDTO(), null);

        // When
        ResultActions perform = this.mvc.perform(
                post("/carts")
                        .content(objectMapper.writeValueAsString(request))
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );


        // Then
        perform
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.message").value("책이 없습니다."))
        ;
    }

    @Test
    @DisplayName("장바구니 저장 성공")
    void saveCart() throws Exception {


        // given
        CartSaveRequest request = new CartSaveRequest(makeUser().toDTO(), makeBook(makePublisher() , makeCategory()).toDTO());

        Cart cart = request.toEntity();

        given(this.cartService.save(request)).willReturn(cart);


        // When
        ResultActions perform = this.mvc.perform(
                post("/carts")
                        .content(objectMapper.writeValueAsString(request))
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );


        // Then
        perform
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.status").value(CartStatus.WAIT.name()))
        ;
    }


    @Test
    @DisplayName("장바구니 수정 Valid 실패")
    void updateValidFail1() throws Exception {


        // given
        int id = 0;
        CartUpdateRequest request = new CartUpdateRequest(makeUser().toDTO(), null, "WAIT");

        // When
        ResultActions perform = this.mvc.perform(
                put("/carts/{id}", id)
                        .content(objectMapper.writeValueAsString(request))
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );


        // Then
        perform
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.message").value("장바구니가 존재하지 않습니다."))
        ;
    }

    @Test
    @DisplayName("장바구니 수정 Enums 실패")
    void updateValidFail2() throws Exception {


        // given
        int id = 0;
        CartUpdateRequest request = new CartUpdateRequest(makeUser().toDTO(), makeBook(makePublisher(),makeCategory()).toDTO(), "asdsadsad");

        // When
        ResultActions perform = this.mvc.perform(
                put("/carts/{id}", id)
                        .content(objectMapper.writeValueAsString(request))
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );


        // Then
        perform
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.message").value(CartConst.notFound))
        ;
    }

    @Test
    @DisplayName("장바구니 수정 조회 실패")
    void updateNotFoundCart() throws Exception {

        // given
        int id = 0;
        CartUpdateRequest request = new CartUpdateRequest(makeUser().toDTO(),makeBook(makePublisher(),makeCategory()).toDTO(), "WAIT");
        given(this.cartService.getCart(id)).willReturn(Optional.empty());

        // When
        ResultActions perform = this.mvc.perform(
                put("/carts/{id}", id)
                        .content(objectMapper.writeValueAsString(request))
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        // Then
        perform
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.message").value(CartConst.notFound))
        ;
    }

    @Test
    @DisplayName("장바구니 수정")
    void updateCart() throws Exception {

        // given
        int id = 1;
        User user = makeUser();
        Book book = makeBook(makePublisher(), makeCategory());
        CartUpdateRequest request = new CartUpdateRequest(user.toDTO(),book.toDTO(), "WAIT");

        Assertions.assertNotNull(user);
        Assertions.assertNotNull(book);
        Assertions.assertNotNull(request);

        Optional<Cart> optional = Optional.of(new Cart(1, makeUser(), makeBook(makePublisher(),makeCategory()), CartStatus.ACTIVE));
        given(this.cartService.getCart(id)).willReturn(optional);

        given(cartService.update(request, optional.get())).willReturn(new Cart(1, makeUser(),makeBook(makePublisher(),makeCategory()), CartStatus.WAIT));


        // When
        ResultActions perform = this.mvc.perform(
                put("/carts/{id}", id)
                        .content(objectMapper.writeValueAsString(request))
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        // Then
        perform
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.status").value(CartStatus.WAIT.name()))
        ;
    }

    @Test
    @DisplayName("장바구니 삭제 실패")
    void deleteNotFoundCart() throws Exception {

        // given
        int id = 0;
        given(this.cartService.getCart(id)).willReturn(Optional.empty());


        // When
        ResultActions perform = this.mvc.perform(
                delete("/carts/{id}", id)
        );

        // Then
        perform
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.message").value(CartConst.notFound))
        ;
    }

    @Test
    @DisplayName("장바구니 삭제")
    void deleteCart() throws Exception {

        // given
        int id = 0;
        Optional<Cart> optional = Optional.of(new Cart());
        given(this.cartService.getCart(id)).willReturn(optional);


        // When
        ResultActions perform = this.mvc.perform(
                delete("/carts/{id}", id)
        );

        // Then
        MvcResult mvcResult = perform
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        Assertions.assertEquals(mvcResult.getResponse().getContentAsString(), "삭제가 완료되었습니다.");
    }

    private User makeUser() {
        return User.builder()
                .id(1)
                .username("유저이름")
                .password("1234")
                .role(RoleType.USER)
                .isMembership(true)
                .isAutoPayment(true)
                .joinTime(LocalDateTime.of(2023, 4, 17, 21, 44, 10))
                .fileInfo(new FileInfo(1, FileType.USER))
                .status(UserStatus.ACTIVE)
                .build();
    }

    private Publisher makePublisher(){
        return Publisher.builder()
                .id(1)
                .username("출판사이름")
                .password("1234")
                .role(RoleType.PUBLISHER)
                .businessNumber("1234")
                .businessName("사업자이름")
                .joinTime(LocalDateTime.of(2023, 4, 17, 21, 44, 10))
                .status(PublisherStatus.ACTIVE)
                .build();
    }

    private Category makeCategory(){
        return Category.builder()
                .id(1)
                .bigCategory(BigCategoryType.경영)
                .smallCategory(SmallCategoryType.경영일반)
                .status(CategoryStatus.ACTIVE)
                .build();
    }

    private Book makeBook(Publisher publisher, Category category) {
        return Book.builder()
                .id(1)
                .publisher(publisher)
                .title("책제목")
                .author("저자이름")
                .price(1000)
                .introduction("책소개")
                .content("책내용")
                .category(category)
                .authorInfo("저자정보")
                .fileInfo(new FileInfo(1, FileType.BOOK))
                .status(BookStatus.ACTIVE)
                .build();
    }

}
