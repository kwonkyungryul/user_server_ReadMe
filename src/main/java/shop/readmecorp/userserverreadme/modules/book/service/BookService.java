package shop.readmecorp.userserverreadme.modules.book.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.readmecorp.userserverreadme.common.exception.Exception400;
import shop.readmecorp.userserverreadme.modules.book.BookConst;
import shop.readmecorp.userserverreadme.modules.book.dto.BookDTO;
import shop.readmecorp.userserverreadme.modules.book.entity.Book;
import shop.readmecorp.userserverreadme.modules.book.repository.BookRepository;
import shop.readmecorp.userserverreadme.modules.book.request.BookSaveRequest;
import shop.readmecorp.userserverreadme.modules.book.request.BookUpdateRequest;
import shop.readmecorp.userserverreadme.modules.book.response.BookDetailResponse;
import shop.readmecorp.userserverreadme.modules.review.ReviewConst;
import shop.readmecorp.userserverreadme.modules.review.entity.Review;
import shop.readmecorp.userserverreadme.modules.review.repository.ReviewRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    private final ReviewRepository reviewRepository;

    public BookService(BookRepository bookRepository, ReviewRepository reviewRepository) {
        this.bookRepository = bookRepository;
        this.reviewRepository = reviewRepository;
    }

    public PageImpl<?> getPage(Pageable pageable) {
        Page<Book> page = bookRepository.findAll(pageable);
        List<BookDTO> content = page.getContent()
                .stream()
                .map(Book::toDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(content, pageable, page.getTotalElements());
    }

    public Optional<Book> getBook(Integer id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isEmpty()) {
            throw new Exception400(BookConst.notFound);
        }
        return optionalBook;
    }

    public BookDetailResponse getBookDetail(Integer id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isEmpty()) {
            throw new Exception400(BookConst.notFound);
        }
        Book book = optionalBook.get();
        List<Review> reviews = reviewRepository.findByBookId(book.getId());
        if (reviews == null) {
            throw new Exception400(BookConst.notFound);
        }

        var reviewDTOList = reviews.stream().map(Review::toDTO).collect(Collectors.toList());

        return BookDetailResponse.builder()
                .publisher(book.getPublisher().toDTO())
                .title(book.getTitle())
                .author(book.getAuthor())
                .price(book.getPrice())
                .introduction(book.getIntroduction())
                .filePath(book.getFilePath())
                .bigCategory(book.getBigCategory().toDTO())
                .smallCategory(book.getSmallCategory().toDTO())
                .authorInfo(book.getAuthorInfo())
                .fileInfo(book.getFileInfo().toDTO())
                .status(book.getStatus().name())
                .reviews(reviewDTOList)
                .build();
    }

    public Book save(BookSaveRequest request) {

        return null;
    }

    public Book update(BookUpdateRequest request, Book book) {
        return null;
    }

    public void delete(Book book) {
    }
}
