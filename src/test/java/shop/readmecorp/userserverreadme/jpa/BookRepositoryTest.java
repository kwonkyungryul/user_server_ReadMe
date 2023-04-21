package shop.readmecorp.userserverreadme.jpa;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;
import shop.readmecorp.userserverreadme.common.jpa.RoleType;
import shop.readmecorp.userserverreadme.modules.book.entity.Book;
import shop.readmecorp.userserverreadme.modules.book.enums.BookStatus;
import shop.readmecorp.userserverreadme.modules.book.repository.BookRepository;
import shop.readmecorp.userserverreadme.modules.category.entity.SmallCategory;
import shop.readmecorp.userserverreadme.modules.category.enums.BigCategoryType;
import shop.readmecorp.userserverreadme.modules.category.enums.CategoryStatus;
import shop.readmecorp.userserverreadme.modules.category.enums.SmallCategoryType;
import shop.readmecorp.userserverreadme.modules.file.entity.FileInfo;
import shop.readmecorp.userserverreadme.modules.file.enums.FileType;
import shop.readmecorp.userserverreadme.modules.publisher.entity.Publisher;
import shop.readmecorp.userserverreadme.modules.publisher.enums.PublisherStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    public void init() {
        setUp();
    }

    @Test
    @Transactional
    public void selectAll() {
        List<Book> books = bookRepository.findAll();
        Assertions.assertNotEquals(books.size(), 0);

        Assertions.assertEquals(books.get(0).getTitle(), "책제목1");
    }

    @Test
    @Transactional
    public void selectAndUpdate() {
        int id = 1;
        Optional<Book> optionalBooks = bookRepository.findById(id);
        if (optionalBooks.isPresent()) {
            Book result = optionalBooks.get();
            Assertions.assertEquals(result.getTitle(), "책제목1");

            String title = "수정 책 제목1";
            result.setTitle(title);
            Book merge = entityManager.merge(result);

            Assertions.assertEquals(merge.getTitle(), "수정 책 제목1");
        } else {
            Assertions.assertNotNull(optionalBooks.get());
        }
    }

    @Test
    @Transactional
    public void insertAndDelete() {
        Book book = setUp();
        Optional<Book> optionalBook = bookRepository.findById(book.getId());

        if (optionalBook.isPresent()) {
            Book result = optionalBook.get();
            Assertions.assertEquals(result.getTitle(), "덕혜옹주");
            entityManager.remove(result);
            Optional<Book> deleteBook = bookRepository.findById(result.getId());
            if (deleteBook.isPresent()) {
                Assertions.assertNull(deleteBook.get());
            }
        } else {
            Assertions.assertNotNull(optionalBook.get());
        }
    }

    public Book setUp() {
        Publisher publisher = entityManager.persist(Publisher.builder()
                .username("dasan@nate.com")
                .password("1234")
                .role(RoleType.PUBLISHER)
                .businessNumber("123-12-12345")
                .businessName("다산북스")
                .joinTime(LocalDateTime.of(2023, 4, 17, 14, 7))
                .status(PublisherStatus.WAIT)
                .build()
        );

        FileInfo fileInfo = entityManager.persist(FileInfo.builder().type(FileType.BOOK).build());

        SmallCategory category = entityManager.persist(SmallCategory.builder()
                .bigCategory(BigCategoryType.역사)
                .smallCategory(SmallCategoryType.독서_에세이)
                .status(CategoryStatus.ACTIVE)
                .build()
        );

        Book book = entityManager.persist(Book.builder()
                .publisher(publisher)
                .title("덕혜옹주")
                .author("권비영")
                .price(12420)
                .introduction("책 소개글")
                .filePath("책 내용")
                .category(category)
                .authorInfo("저자 소개")
                .fileInfo(fileInfo)
                .status(BookStatus.WAIT)
                .build());
        return book;
    }
}