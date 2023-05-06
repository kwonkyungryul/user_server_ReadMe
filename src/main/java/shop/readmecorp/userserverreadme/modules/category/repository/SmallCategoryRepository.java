package shop.readmecorp.userserverreadme.modules.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.readmecorp.userserverreadme.modules.category.entity.SmallCategory;

import java.util.Collection;
import java.util.List;

public interface SmallCategoryRepository extends JpaRepository<SmallCategory, Integer> {

    List<SmallCategory> findByBigCategoryId(Integer bigCategoryId);
    List<SmallCategory> findByBigCategoryIdIn(List<Integer> categoryIds);
}
