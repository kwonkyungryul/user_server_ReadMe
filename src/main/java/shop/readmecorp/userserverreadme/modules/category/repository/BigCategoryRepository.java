package shop.readmecorp.userserverreadme.modules.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.readmecorp.userserverreadme.modules.category.entity.BigCategory;

public interface BigCategoryRepository extends JpaRepository<BigCategory, Integer> {
}
