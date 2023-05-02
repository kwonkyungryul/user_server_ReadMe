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
import shop.readmecorp.userserverreadme.modules.book.enums.BookStatus;
import shop.readmecorp.userserverreadme.modules.book.repository.BookRepository;
import shop.readmecorp.userserverreadme.modules.book.repository.HeartRepository;
import shop.readmecorp.userserverreadme.modules.book.request.BookSaveRequest;
import shop.readmecorp.userserverreadme.modules.book.request.BookUpdateRequest;
import shop.readmecorp.userserverreadme.modules.book.response.BookDetailResponse;
import shop.readmecorp.userserverreadme.modules.file.entity.File;
import shop.readmecorp.userserverreadme.modules.file.entity.FileInfo;
import shop.readmecorp.userserverreadme.modules.file.repository.FileInfoRepository;
import shop.readmecorp.userserverreadme.modules.file.repository.FileRepository;
import shop.readmecorp.userserverreadme.modules.review.entity.Review;
import shop.readmecorp.userserverreadme.modules.review.enums.ReviewStatus;
import shop.readmecorp.userserverreadme.modules.review.repository.ReviewRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    private final ReviewRepository reviewRepository;

    private final FileInfoRepository fileInfoRepository;

    private final FileRepository fileRepository;

    public BookService(BookRepository bookRepository, ReviewRepository reviewRepository, FileInfoRepository fileInfoRepository, FileRepository fileRepository) {
        this.bookRepository = bookRepository;
        this.reviewRepository = reviewRepository;
        this.fileInfoRepository = fileInfoRepository;
        this.fileRepository = fileRepository;
    }

    public PageImpl<?> getPage(Pageable pageable) {
        Page<Book> page = bookRepository.findAll(pageable);
        List<BookDTO> content = page.getContent()
                .stream()
                .filter(book -> book.getStatus().equals(BookStatus.ACTIVE))
                .map(Book::toDTO)
                .collect(Collectors.toList());

        for (int i = 0; i < content.size(); i++) {
            List<File> files = fileRepository.findByFileInfo_Id(page.getContent().get(i).getFileInfo().getId());
            Double stars = reviewRepository.findAvgStars(content.get(i).getId());
            content.get(i).setFileDTO(File.toDTO(files));
            content.get(i).setStars(Math.ceil(stars));
        }

        // TODO 로그인 시 좋아요 체크(isHeart) 해야함. 아래 것들도 마찬가지

        return new PageImpl<>(content, pageable, page.getTotalElements());
    }

    public PageImpl<?> getRecommend(Pageable pageable) {
        Page<Book> page = bookRepository.findByBookHeartCount(pageable);

        List<BookDTO> content = page.getContent()
                .stream()
                .filter(book -> book.getStatus().equals(BookStatus.ACTIVE))
                .map(Book::toDTO)
                .collect(Collectors.toList());

        for (int i = 0; i < content.size(); i++) {
            List<File> files = fileRepository.findByFileInfo_Id(page.getContent().get(i).getFileInfo().getId());
            Double stars = reviewRepository.findAvgStars(content.get(i).getId());
            if (stars != null) {
                content.get(i).setStars(Math.ceil((stars * 10) / 10));
            } else {
                content.get(i).setStars(0.0);
            }
            content.get(i).setFileDTO(File.toDTO(files));
        }

        return new PageImpl<>(content, pageable, page.getTotalElements());
    }

    public PageImpl<?> getBestSellers(Pageable pageable) {
        Page<Book> page = bookRepository.findByBookPaymentDESC(pageable);

        List<BookDTO> content = page.getContent()
                .stream()
                .filter(book -> book.getStatus().equals(BookStatus.ACTIVE))
                .map(Book::toDTO)
                .collect(Collectors.toList());

        for (int i = 0; i < content.size(); i++) {
            List<File> files = fileRepository.findByFileInfo_Id(page.getContent().get(i).getFileInfo().getId());
            Double stars = reviewRepository.findAvgStars(content.get(i).getId());
            content.get(i).setFileDTO(File.toDTO(files));
            if (stars != null) {
                content.get(i).setStars(Math.ceil((stars * 10) / 10));
            } else {
                content.get(i).setStars(0.0);
            }
        }

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

        Optional<FileInfo> optionalFileInfo = fileInfoRepository.findById(book.getFileInfo().getId());
        List<File> files = fileRepository.findByFileInfo_Id(optionalFileInfo.get().getId());

        var reviewDTOList = reviews.stream()
                .filter(review -> review.getStatus().equals(ReviewStatus.ACTIVE))
                .map(Review::toDTO)
                .collect(Collectors.toList());

        return BookDetailResponse.builder()
                .id(id)
                .publisher(book.getPublisher().toDTO())
                .title(book.getTitle())
                .author(book.getAuthor())
                .price(book.getPrice())
                .introduction(book.getIntroduction())
                .bigCategory(book.getBigCategory().toDTO())
                .smallCategory(book.getSmallCategory().toDTO())
                .authorInfo(book.getAuthorInfo())
                .epubUrl(files.get(0).getFileUrl())
                .coverUrl(files.get(1).getFileUrl())
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
