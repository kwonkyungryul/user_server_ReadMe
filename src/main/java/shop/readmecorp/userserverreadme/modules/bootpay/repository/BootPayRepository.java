package shop.readmecorp.userserverreadme.modules.bootpay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.readmecorp.userserverreadme.modules.bootpay.entity.BootPayMaster;

public interface BootPayRepository extends JpaRepository<BootPayMaster, Integer> {
}
