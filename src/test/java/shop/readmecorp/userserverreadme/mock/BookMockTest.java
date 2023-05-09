//package shop.readmecorp.userserverreadme.mock;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.transaction.annotation.Transactional;
//import shop.readmecorp.userserverreadme.common.jpa.RoleType;
//import shop.readmecorp.userserverreadme.modules.book.BookConst;
//import shop.readmecorp.userserverreadme.modules.book.controller.BookController;
//import shop.readmecorp.userserverreadme.modules.book.entity.Book;
//import shop.readmecorp.userserverreadme.modules.book.enums.BookStatus;
//import shop.readmecorp.userserverreadme.modules.book.request.BookSaveRequest;
//import shop.readmecorp.userserverreadme.modules.book.service.BookService;
//import shop.readmecorp.userserverreadme.modules.category.entity.SmallCategory;
//import shop.readmecorp.userserverreadme.modules.category.enums.BigCategoryType;
//import shop.readmecorp.userserverreadme.modules.category.enums.CategoryStatus;
//import shop.readmecorp.userserverreadme.modules.category.enums.SmallCategoryType;
//import shop.readmecorp.userserverreadme.modules.file.entity.FileInfo;
//import shop.readmecorp.userserverreadme.modules.file.enums.FileType;
//import shop.readmecorp.userserverreadme.modules.publisher.entity.Publisher;
//import shop.readmecorp.userserverreadme.modules.publisher.enums.PublisherStatus;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//import static org.mockito.BDDMockito.given;
//import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
//import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(BookController.class)
//@MockBean(JpaMetamodelMappingContext.class)
//public class BookMockTest {
//
//    @Autowired
//    MockMvc mvc;
//
//    @MockBean
//    private BookService bookService;
//
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    @Test
//    public void getPage() throws Exception {
//        Pageable pageable = PageRequest.of(1, 10);
//        Page<Book> page = new PageImpl<>(
//                List.of(
//                        Book.builder()
//                                .id(1)
//                                .publisher(getPublisher())
//                                .title("덕혜옹주")
//                                .author("권비영")
//                                .price(12420)
//                                .introduction("책 소개글")
//                                .content("책 내용")
//                                .category(getCategory())
//                                .authorInfo("저자 소개")
//                                .fileInfo(getFileInfo())
//                                .status(BookStatus.WAIT)
//                                .build(),
//                        Book.builder()
//                                .id(2)
//                                .publisher(getPublisher())
//                                .title("덕혜옹주2")
//                                .author("권비영2")
//                                .price(22420)
//                                .introduction("책 소개글2")
//                                .content("책 내용2")
//                                .category(getCategory())
//                                .authorInfo("저자 소개2")
//                                .fileInfo(getFileInfo())
//                                .status(BookStatus.WAIT)
//                                .build()
//                )
//        );
//
//        // given
//        int id = 1;
//        given(this.bookService.getPage(pageable)).willReturn(page);
//
//        // when
//        ResultActions perform = this.mvc.perform(
//                get("/books/?page={page}&size={size}", 1, 10)
//        );
//
//        perform
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(jsonPath("$.content[0].id").value(1))
//                .andExpect(jsonPath("$.content[0].publisher.username").value("dasan@nate.com"))
//                .andExpect(jsonPath("$.content[0].title").value("덕혜옹주"))
//                .andExpect(jsonPath("$.content[0].author").value("권비영"))
//                .andExpect(jsonPath("$.content[0].category.bigCategory").value(BigCategoryType.역사.name()))
//                .andExpect(jsonPath("$.content[0].category.smallCategory").value(SmallCategoryType.독서_에세이.name()))
//                .andExpect(jsonPath("$.content[0].category.status").value(CategoryStatus.ACTIVE.name()))
//                .andExpect(jsonPath("$.content[0].status").value(BookStatus.WAIT.name()))
//
//                .andExpect(jsonPath("$.content[1].id").value(2))
//                .andExpect(jsonPath("$.content[1].publisher.username").value("dasan@nate.com"))
//                .andExpect(jsonPath("$.content[1].title").value("덕혜옹주2"))
//                .andExpect(jsonPath("$.content[1].author").value("권비영2"))
//                .andExpect(jsonPath("$.content[0].category.bigCategory").value(BigCategoryType.역사.name()))
//                .andExpect(jsonPath("$.content[0].category.smallCategory").value(SmallCategoryType.독서_에세이.name()))
//                .andExpect(jsonPath("$.content[1].category.status").value(CategoryStatus.ACTIVE.name()))
//                .andExpect(jsonPath("$.content[1].status").value(BookStatus.WAIT.name()))
//        ;
//    }
//
//    @Test
//    public void getBookFail() throws Exception {
//        // given
//        int id = 0;
//        given(this.bookService.getBook(id)).willReturn(Optional.empty());
//
//        // when
//        ResultActions perform = mvc.perform(
//                get("/books/{id}", id)
//        );
//
//        // then
//        perform
//                .andExpect(status().isBadRequest())
//                .andDo(print())
//                .andExpect(jsonPath("$.message").value(BookConst.notFound))
//        ;
//    }
//
//    @Test
//    public void getBook() throws Exception {
//        // given
//        int id = 1;
//        given(this.bookService.getBook(id)).willReturn(
//                Optional.of(Book.builder()
//                        .id(1)
//                        .publisher(getPublisher())
//                        .title("덕혜옹주")
//                        .author("권비영")
//                        .price(12420)
//                        .introduction("책 소개글")
//                        .content("책 내용")
//                        .category(getCategory())
//                        .authorInfo("저자 소개")
//                        .fileInfo(getFileInfo())
//                        .status(BookStatus.WAIT)
//                        .build())
//        );
//
//        // when
//        ResultActions perform = this.mvc.perform(
//                get("/books/{id}", id)
//        );
//
//        // then
//        perform
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.publisher.username").value("dasan@nate.com"))
//                .andExpect(jsonPath("$.title").value("덕혜옹주"))
//                .andExpect(jsonPath("$.author").value("권비영"))
//                .andExpect(jsonPath("$.category.bigCategory").value(BigCategoryType.역사.name()))
//                .andExpect(jsonPath("$.category.smallCategory").value(SmallCategoryType.독서_에세이.name()))
//                .andExpect(jsonPath("$.category.status").value(CategoryStatus.ACTIVE.name()))
//                .andExpect(jsonPath("$.status").value(BookStatus.WAIT.name()))
//        ;
//    }
//
//    @Test
//    public void saveBookFail() throws Exception {
//        // given
//        BookSaveRequest request = BookSaveRequest.builder()
//                .publisher(getPublisher().toDTO())
//                .title("")
//                .author("홍길동")
//                .price(123)
//                .introduction("홍길동전 소개글")
//                .content("홍길동전 내용")
//                .category(getCategory().toDTO())
//                .authorInfo("홍길동 소개")
//                .files(null)
//                .build();
//
//        // when
//        ResultActions perform = this.mvc.perform(
//                post("/books")
//                        .content(objectMapper.writeValueAsString(request))
//                        .accept(MediaType.APPLICATION_JSON_VALUE)
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//        );
//
//        // then
//        perform
//                .andExpect(status().isBadRequest())
//                .andDo(print())
//                .andExpect(jsonPath("$.message").value("책 제목을 입력해주세요."))
//        ;
//    }
//
//    @Test
//    @Transactional
//    public void saveBook() throws Exception {
//        // given
//        BookSaveRequest request = BookSaveRequest.builder()
//                .publisher(getPublisher().toDTO())
//                .title("홍길동전")
//                .author("홍길동")
//                .price(123)
//                .introduction("홍길동전 소개글")
//                .content("홍길동전 내용")
//                .category(getCategory().toDTO())
//                .authorInfo("홍길동 소개")
//                .files(null)
//                .build();
//
////        Book book = Book.builder()
////                .id(1)
////                .publisher(getPublisher())
////                .title(request.getTitle())
////                .author(request.getAuthor())
////                .price(request.getPrice())
////                .introduction(request.getIntroduction())
////                .content(request.getContent())
////                .category(getCategory())
////                .authorInfo(request.getAuthorInfo())
////                .fileInfo(getFileInfo())
////                .status(BookStatus.WAIT)
////                .build();
//
//        given(this.bookService.save(request))
//                .willReturn(
//                        request.toEntity()
//                );
//
//        // when
//        ResultActions perform = this.mvc.perform(
//                post("/books")
//                        .content(objectMapper.writeValueAsString(request))
//                        .accept(MediaType.APPLICATION_JSON_VALUE)
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//        );
//
//        perform
//                .andExpect(status().isOk())
//                .andDo(print())
//        ;
//    }
//
//    @Test
//    public void updateValidFail() throws Exception {
//
//    }
//
//    @Test
//    public void updateEnumFail() throws Exception {
//
//    }
//
//    @Test
//    public void updateBook() throws Exception {
//
//    }
//
//    @Test
//    public void deleteNotFoundBook() throws Exception {
//
//    }
//
//    @Test
//    public void deleteBook() throws Exception {
//
//    }
//
//    private Publisher getPublisher() {
//        return Publisher.builder()
//                .id(1)
//                .username("dasan@nate.com")
//                .password("1234")
//                .role(RoleType.PUBLISHER)
//                .businessNumber("123-12-12345")
//                .businessName("다산북스")
//                .joinTime(LocalDateTime.of(2023, 4, 18, 11, 10, 10))
//                .status(PublisherStatus.WAIT)
//                .build();
//    }
//
//    private FileInfo getFileInfo() {
//        return FileInfo.builder().id(1).type(FileType.BOOK).build();
//    }
//
//    private SmallCategory getCategory() {
//        return SmallCategory.builder()
//                .id(1)
//                .bigCategory(BigCategoryType.역사)
//                .smallCategory(SmallCategoryType.독서_에세이)
//                .status(CategoryStatus.ACTIVE)
//                .build();
//    }
//}
