package shop.readmecorp.userserverreadme.modules.category.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.readmecorp.userserverreadme.modules.category.dto.BigCategoryDTO;
import shop.readmecorp.userserverreadme.modules.category.dto.SmallCategoryDTO;
import shop.readmecorp.userserverreadme.modules.category.entity.BigCategory;
import shop.readmecorp.userserverreadme.modules.category.entity.SmallCategory;
import shop.readmecorp.userserverreadme.modules.category.repository.BigCategoryRepository;
import shop.readmecorp.userserverreadme.modules.category.repository.SmallCategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(readOnly = true)
public class CategoryService {
    private final SmallCategoryRepository smallCategoryRepository;
    private final BigCategoryRepository bigCategoryRepository;

    public CategoryService(SmallCategoryRepository smallCategoryRepository, BigCategoryRepository bigCategoryRepository) {
        this.smallCategoryRepository = smallCategoryRepository;
        this.bigCategoryRepository = bigCategoryRepository;
    }

    public List<BigCategoryDTO> getCategories() {
        List<BigCategory> bigCategoryList = bigCategoryRepository.findAll();
        List<BigCategoryDTO> bigCategoryDTOList = bigCategoryList.stream().map(BigCategory::toDTO).collect(Collectors.toList());

        List<Integer> bigCategoryIds = bigCategoryList.stream().map(BigCategory :: getId).collect(Collectors.toList());
        List<SmallCategory> smallCategoryList = smallCategoryRepository.findByBigCategoryIdIn(bigCategoryIds);

        bigCategoryDTOList.forEach(
                bigCategoryDTO -> bigCategoryDTO.setSmallCategory (
                    smallCategoryList.stream()
                            .filter(smallCategory -> smallCategory.getBigCategory().getId().equals(bigCategoryDTO.getId()))
                            .map(SmallCategory::toDTO)
                            .collect(Collectors.toList())
                )
        );
         return bigCategoryDTOList;
    }
}
