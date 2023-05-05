package shop.readmecorp.userserverreadme.modules.category.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import shop.readmecorp.userserverreadme.common.exception.Exception400;
import shop.readmecorp.userserverreadme.modules.book.dto.BookDTO;
import shop.readmecorp.userserverreadme.modules.book.entity.Book;
import shop.readmecorp.userserverreadme.modules.book.enums.BookStatus;
import shop.readmecorp.userserverreadme.modules.book.repository.BookRepository;
import shop.readmecorp.userserverreadme.modules.category.dto.BigCategoryDTO;
import shop.readmecorp.userserverreadme.modules.category.dto.SmallCategoryDTO;
import shop.readmecorp.userserverreadme.modules.category.entity.BigCategory;
import shop.readmecorp.userserverreadme.modules.category.entity.SmallCategory;
import shop.readmecorp.userserverreadme.modules.category.enums.CategoryConst;
import shop.readmecorp.userserverreadme.modules.category.repository.BigCategoryRepository;
import shop.readmecorp.userserverreadme.modules.category.repository.SmallCategoryRepository;
import shop.readmecorp.userserverreadme.modules.category.response.CategoryResponse;
import shop.readmecorp.userserverreadme.modules.file.entity.File;
import shop.readmecorp.userserverreadme.modules.file.repository.FileRepository;
import shop.readmecorp.userserverreadme.modules.review.repository.ReviewRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryService {

    private final SmallCategoryRepository smallCategoryRepository;

    private final BigCategoryRepository bigCategoryRepository;

    private final BookRepository bookRepository;

    private final FileRepository fileRepository;

    private final ReviewRepository reviewRepository;

    public CategoryService(SmallCategoryRepository smallCategoryRepository, BigCategoryRepository bigCategoryRepository, BookRepository bookRepository, FileRepository fileRepository, ReviewRepository reviewRepository) {
        this.smallCategoryRepository = smallCategoryRepository;
        this.bigCategoryRepository = bigCategoryRepository;
        this.bookRepository = bookRepository;
        this.fileRepository = fileRepository;
        this.reviewRepository = reviewRepository;
    }

    // TODO 완전 바뀔 예정
    public CategoryResponse getCategories(Integer bigCategoryId, Integer smallCategoryId, Pageable pageable) {
        Optional<BigCategory> optionalBigCategory = bigCategoryRepository.findById(bigCategoryId);
        if (optionalBigCategory.isEmpty()) {
            throw new Exception400(CategoryConst.notFound);
        }

        BigCategory bigCategory = optionalBigCategory.get();
        List<BigCategory> bigCategoryList = bigCategoryRepository.findAll();
        List<SmallCategory> smallCategoryList = smallCategoryRepository.findByBigCategoryId(bigCategory.getId());

        Page<Book> page = null;
        List<BookDTO> content = new ArrayList<>();
        if (smallCategoryId == null) {
            page = bookRepository.findByBigCategoryId(bigCategoryId, pageable);
            content = page.getContent()
                    .stream()
                    .filter(book -> book.getStatus().equals(BookStatus.ACTIVE) && book.getBigCategory().getId().equals(bigCategory.getId()))
                    .map(Book::toDTO)
                    .collect(Collectors.toList());
        } else {
            page = bookRepository.findByBigCategoryIdAndSmallCategoryId(bigCategoryId, smallCategoryId, pageable);
            content = page.getContent()
                    .stream()
                    .filter(book -> book.getStatus().equals(BookStatus.ACTIVE) && book.getBigCategory().getId().equals(bigCategory.getId()))
                    .map(Book::toDTO)
                    .collect(Collectors.toList());
        }

        List<BigCategoryDTO> bigCategoryDTOList = bigCategoryList.stream().map(BigCategory::toDTO).collect(Collectors.toList());
        List<SmallCategoryDTO> smallCategoryDTOList = smallCategoryList.stream().map(SmallCategory::toDTO).collect(Collectors.toList());

        for (int i = 0; i < content.size(); i++) {
            File epubFiles = fileRepository.findByFileInfo_Id(page.getContent().get(i).getEpub().getId());
            File coverFiles = fileRepository.findByFileInfo_Id(page.getContent().get(i).getCover().getId());
            Double stars = reviewRepository.findAvgStars(content.get(i).getId());
            content.get(i).setEpubFile(epubFiles.toDTO());
            content.get(i).setCoverFile(coverFiles.toDTO());

            // TODO 로그인 시 좋아요 체크(isHeart) 해야함. 아래 것들도 마찬가지
            content.get(i).setIsHeart(true);

            if (stars != null) {
                content.get(i).setStars(Math.ceil((stars * 10) / 10));
            } else {
                content.get(i).setStars(0.0);
            }
        }

        return CategoryResponse.builder()
                .bigCategory(bigCategoryDTOList)
                .smallCategory(smallCategoryDTOList)
                .books(new PageImpl<>(content, pageable, page.getTotalElements()))
                .build();
    }
}
