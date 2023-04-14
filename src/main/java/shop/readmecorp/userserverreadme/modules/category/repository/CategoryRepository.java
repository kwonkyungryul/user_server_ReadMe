package shop.readmecorp.userserverreadme.modules.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.readmecorp.userserverreadme.modules.category.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
