package shop.readmecorp.userserverreadme.modules.category.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.readmecorp.userserverreadme.modules.book.dto.ResponseDTO;
import shop.readmecorp.userserverreadme.modules.category.service.CategoryService;


@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<?> getCategories(Integer bigCategoryId, Integer smallCategoryId, Pageable pageable) {

        return ResponseEntity.ok(new ResponseDTO<>(1, "카테고리 조회 성공", categoryService.getCategories(bigCategoryId, smallCategoryId, pageable)));
    }
}
