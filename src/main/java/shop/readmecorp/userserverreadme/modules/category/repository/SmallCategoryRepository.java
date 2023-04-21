package shop.readmecorp.userserverreadme.modules.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.readmecorp.userserverreadme.modules.category.entity.SmallCategory;

public interface SmallCategoryRepository extends JpaRepository<SmallCategory, Integer> {
}
