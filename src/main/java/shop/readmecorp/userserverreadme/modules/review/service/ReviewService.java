package shop.readmecorp.userserverreadme.modules.review.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.readmecorp.userserverreadme.modules.book.BookConst;
import shop.readmecorp.userserverreadme.modules.book.dto.BookDTO;
import shop.readmecorp.userserverreadme.modules.book.dto.BookToReviewDTO;
import shop.readmecorp.userserverreadme.modules.book.entity.Book;
import shop.readmecorp.userserverreadme.modules.book.entity.Heart;
import shop.readmecorp.userserverreadme.modules.book.repository.HeartRepository;
import shop.readmecorp.userserverreadme.modules.category.entity.BigCategory;
import shop.readmecorp.userserverreadme.modules.file.entity.File;
import shop.readmecorp.userserverreadme.modules.file.repository.FileRepository;
import shop.readmecorp.userserverreadme.modules.review.dto.MyReviewDTO;
import shop.readmecorp.userserverreadme.modules.review.dto.ReviewDTO;
import shop.readmecorp.userserverreadme.modules.review.dto.ReviewNoneBookDTO;
import shop.readmecorp.userserverreadme.modules.review.entity.Review;
import shop.readmecorp.userserverreadme.modules.review.enums.ReviewStatus;
import shop.readmecorp.userserverreadme.modules.review.repository.ReviewRepository;
import shop.readmecorp.userserverreadme.modules.review.request.ReviewSaveRequest;
import shop.readmecorp.userserverreadme.modules.user.entity.User;
import shop.readmecorp.userserverreadme.util.DateTimeConverter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;

    private final FileRepository fileRepository;

    private final HeartRepository heartRepository;

    public ReviewService(ReviewRepository reviewRepository, FileRepository fileRepository, HeartRepository heartRepository) {
        this.reviewRepository = reviewRepository;
        this.fileRepository = fileRepository;
        this.heartRepository = heartRepository;
    }

    public Page<ReviewNoneBookDTO> getReviews(Integer bookId, ReviewStatus status, Pageable pageable) {
        Page<Review> page = reviewRepository.findByStatusAndBookId(status, bookId, pageable);
        return page.map(Review::toNoneBookDTO);
    }

    public List<MyReviewDTO> getList(User user) {
        List<Review> reviewList = reviewRepository.findByStatusAndUser(ReviewStatus.ACTIVE, user);
        return reviewList
                .stream().map(review -> {
                    MyReviewDTO myReviewDTO = new MyReviewDTO();
                    Book book = review.getBook();
                    BookToReviewDTO bookToReviewDTO = book.toBookToReviewDTO();

                    List<File> coverFiles = fileRepository.findByFileInfo_Id(book.getCover().getId());
                    if (coverFiles.size() == 0) {
                        bookToReviewDTO.setCoverUrl(null);
                    } else {
                        bookToReviewDTO.setCoverUrl(coverFiles.get(0).getFileUrl());
                    }

                    myReviewDTO.setId(review.getId());
                    myReviewDTO.setBook(bookToReviewDTO);

                    Double stars = reviewRepository.findAvgStars(book.getId());
                    if (stars != null) {
                        myReviewDTO.setStars(Math.ceil((stars * 10) / 10));
                    } else {
                        myReviewDTO.setStars(0.0);
                    }

                    myReviewDTO.setContent(review.getContent());
                    myReviewDTO.setWriteTime(DateTimeConverter.localDateTimeToString(review.getCreatedDate()));

                    return myReviewDTO;
                }).collect(Collectors.toList());
    }

    public Optional<Review> getDetail(Integer id) {
        return reviewRepository.findByStatusAndId(ReviewStatus.ACTIVE, id);
    }

    @Transactional
    public ReviewDTO save(User user, ReviewSaveRequest request, Book book) {
        BookDTO bookDTO = book.toDTO();
        List<File> epubFiles = fileRepository.findByFileInfo_Id(book.getEpub().getId());
        List<File> coverFiles = fileRepository.findByFileInfo_Id(book.getCover().getId());
        if (epubFiles.size() == 0 && coverFiles.size() == 0) {
            bookDTO.setEpubFile(BookConst.defaultBookFileDTO);
        } else {
            bookDTO.setEpubFile(epubFiles.get(0).toDTO());
            bookDTO.setCoverFile(coverFiles.get(0).toDTO());
        }
        Review review = reviewRepository.save(
                new Review(null, user, book, request.getStars(), request.getContent(), ReviewStatus.ACTIVE)
        );

        Optional<Heart> optionalHeart = heartRepository.heartCount(book.getId(), user.getId());
        BigCategory bigCategory = book.getSmallCategory().getBigCategory();

        ReviewDTO reviewDTO = review.toDTO();
        reviewDTO.getBook().setIsHeart(false);
        if (optionalHeart.isPresent()) {
            bookDTO.setIsHeart(true);
        }
        reviewDTO.getBook().setBigCategory(bigCategory.toSingleDTO());
        Double stars = reviewRepository.findAvgStars(bookDTO.getId());
        if (stars != null) {
            stars = (Math.ceil((stars * 10) / 10));
        } else {
            stars = 0.0;
        }
        reviewDTO.getBook().setStars(stars);
        reviewDTO.getBook().setEpubFile(bookDTO.getEpubFile());
        reviewDTO.getBook().setCoverFile(bookDTO.getCoverFile());
        return reviewDTO;
    }

    @Transactional
    public void delete(Review review) {
        review.setStatus(ReviewStatus.DELETE);
        reviewRepository.save(review);
    }
}
