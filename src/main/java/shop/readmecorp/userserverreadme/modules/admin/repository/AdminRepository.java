package shop.readmecorp.userserverreadme.modules.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.readmecorp.userserverreadme.modules.admin.entity.Admin;

public interface AdminRepository extends JpaRepository <Admin, Integer> {
}
