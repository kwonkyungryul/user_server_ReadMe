package shop.readmecorp.userserverreadme.modules.book.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.readmecorp.userserverreadme.auth.session.MyUserDetails;
import shop.readmecorp.userserverreadme.modules.common.enums.MainTabType;
import shop.readmecorp.userserverreadme.modules.book.BookConst;
import shop.readmecorp.userserverreadme.modules.book.dto.BookDTO;
import shop.readmecorp.userserverreadme.modules.book.entity.Book;
import shop.readmecorp.userserverreadme.modules.book.entity.Heart;
import shop.readmecorp.userserverreadme.modules.book.enums.BookStatus;
import shop.readmecorp.userserverreadme.modules.book.enums.HeartStatus;
import shop.readmecorp.userserverreadme.modules.book.repository.BookRepository;
import shop.readmecorp.userserverreadme.modules.book.repository.HeartRepository;
import shop.readmecorp.userserverreadme.modules.book.response.BookDetailResponse;
import shop.readmecorp.userserverreadme.modules.category.entity.BigCategory;
import shop.readmecorp.userserverreadme.modules.category.entity.SmallCategory;
import shop.readmecorp.userserverreadme.modules.category.repository.SmallCategoryRepository;
import shop.readmecorp.userserverreadme.modules.file.dto.FileDTO;
import shop.readmecorp.userserverreadme.modules.file.entity.File;
import shop.readmecorp.userserverreadme.modules.file.repository.FileRepository;
import shop.readmecorp.userserverreadme.modules.payment.entity.BookPayment;
import shop.readmecorp.userserverreadme.modules.payment.enums.PaymentStatus;
import shop.readmecorp.userserverreadme.modules.payment.repository.BookPaymentRepository;
import shop.readmecorp.userserverreadme.modules.review.dto.ReviewNoneBookDTO;
import shop.readmecorp.userserverreadme.modules.review.repository.ReviewRepository;
import shop.readmecorp.userserverreadme.modules.user.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    private final ReviewRepository reviewRepository;

    private final FileRepository fileRepository;

    private final HeartRepository heartRepository;

    private final BookPaymentRepository bookPaymentRepository;

    private final SmallCategoryRepository smallCategoryRepository;


    public BookService(BookRepository bookRepository, ReviewRepository reviewRepository, FileRepository fileRepository, HeartRepository heartRepository, BookPaymentRepository bookPaymentRepository, SmallCategoryRepository smallCategoryRepository) {
        this.bookRepository = bookRepository;
        this.reviewRepository = reviewRepository;
        this.fileRepository = fileRepository;
        this.heartRepository = heartRepository;
        this.bookPaymentRepository = bookPaymentRepository;
        this.smallCategoryRepository = smallCategoryRepository;
    }

    public Page<BookDTO> getPage (
        Integer bigCategoryId,
        Integer smallCategoryId,
        Pageable pageable,
        String status,
        MyUserDetails myUserDetails
    ) {
        Page<Book> page = new PageImpl<>(List.of(), pageable, 0);

        // 전체 (bigCategoryId, smallCategoryID)
        if (status.equals(MainTabType.ALL.getRequestName())) {
            if (bigCategoryId != 0) {
                if (smallCategoryId != 0) {
                    page = bookRepository.findByStatusAndSmallCategoryId(BookStatus.ACTIVE, smallCategoryId, pageable);
                } else {
                    List<Integer> smallCategoryIds = smallCategoryRepository.findByBigCategoryId(bigCategoryId).stream().map(SmallCategory::getId).collect(Collectors.toList());
                    page = bookRepository.findByStatusAndSmallCategoryIdIn(BookStatus.ACTIVE, smallCategoryIds, pageable);
                }
            } else {
                page = bookRepository.findByStatus(pageable, BookStatus.ACTIVE);
            }

        // heart 가 많은 순
        } else if (status.equals(MainTabType.RECOMMEND.getRequestName())) {
            page = bookRepository.findByBookHeartCount(pageable, BookStatus.ACTIVE);

        // bestSeller -> payment 판매순
        } else if (status.equals(MainTabType.BESTSELLER.getRequestName())) {
            page = bookRepository.findByBookPaymentDESC(pageable, BookStatus.ACTIVE);

        // 신간 OrderBy id Desc
        } else if (status.equals(MainTabType.NEW.getRequestName())) {
            page = bookRepository.findByStatusOrderByIdDesc(pageable, BookStatus.ACTIVE);

        }

        return page.map(book -> getBookDTO(book, myUserDetails));
    }

    public Optional<Book> getBook(Integer id) {
        return bookRepository.findById(id);
    }

    @Transactional
    public List<Book> getBooks(List<Integer> bookIds) {
        return bookRepository.findByIdInAndStatusNot(bookIds, BookStatus.DELETE);
    }

    public BookDetailResponse getBookDetail(User user, Book book, FileDTO epubFileDTO, FileDTO coverFileDTO, Page<ReviewNoneBookDTO> reviewDTOList) {
        Boolean isHeart = heartRepository.findByUserAndStatusNotAndBook(user, HeartStatus.DELETE, book).isPresent();
        BookPayment userBookPayment = bookPaymentRepository.findByStatusNotAndUserAndBook(PaymentStatus.DELETE, user, book);
        return BookDetailResponse.builder()
                .id(book.getId())
                .publisher(book.getPublisher().toDTO())
                .title(book.getTitle())
                .author(book.getAuthor())
                .price(book.getPrice())
                .introduction(book.getIntroduction())
                .bigCategory(book.getSmallCategory().getBigCategory().toSingleDTO())
                .smallCategory(book.getSmallCategory().toDTO())
                .authorInfo(book.getAuthorInfo())
                .isHeart(isHeart)
                .isPurchase(userBookPayment != null)
                .epubFile(epubFileDTO)
                .coverFile(coverFileDTO)
                .reviews(reviewDTOList)
                .build();
    }

    public List<BookDTO> getSearch(
            String keyword,
            MyUserDetails myUserDetails
    ) {
        return bookRepository.findByStatusNotAndTitleLike(BookStatus.DELETE, "%" + keyword + "%").stream()
                .map(book -> {
                    return getBookDTO(book, myUserDetails);
                })
                .collect(Collectors.toList());
    }


    private BookDTO getBookDTO(Book book, MyUserDetails myUserDetails) {
        BigCategory bigCategory = book.getSmallCategory().getBigCategory();
        BookDTO bookDTO = book.toDTO();
        List<File> epubFiles = fileRepository.findByFileInfo_Id(book.getEpub().getId());
        if (epubFiles.size() == 0) {
            bookDTO.setEpubFile(BookConst.defaultBookFileDTO);
        } else {
            bookDTO.setEpubFile(epubFiles.get(0).toDTO());
        }

        List<File> coverFiles = fileRepository.findByFileInfo_Id(book.getCover().getId());
        if (epubFiles.size() == 0) {
            bookDTO.setCoverFile(BookConst.defaultBookFileDTO);
        } else {
            bookDTO.setCoverFile(coverFiles.get(0).toDTO());
        }

        Double stars = reviewRepository.findAvgStars(bookDTO.getId());
        if (stars != null) {
            bookDTO.setStars(Math.ceil((stars * 10) / 10));
        } else {
            bookDTO.setStars(0.0);
        }

        bookDTO.setIsHeart(false);
        if (myUserDetails != null) {
            User user = myUserDetails.getUser();
            Optional<Heart> optionalHeart = heartRepository.heartCount(book.getId(), user.getId());
            if (optionalHeart.isPresent()) {
                bookDTO.setIsHeart(true);
            }
        }
        bookDTO.setBigCategory(bigCategory.toSingleDTO());
        return bookDTO;
    }
}
