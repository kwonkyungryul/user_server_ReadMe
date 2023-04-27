package shop.readmecorp.userserverreadme.modules.bootpay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.readmecorp.userserverreadme.modules.bootpay.entity.BootPayLog;

public interface BootPayLogRepository extends JpaRepository<BootPayLog, Integer> {
}
