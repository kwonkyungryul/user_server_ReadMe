package shop.readmecorp.userserverreadme.modules.bootpay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.readmecorp.userserverreadme.modules.bootpay.entity.BootPayMaster;
import shop.readmecorp.userserverreadme.modules.bootpay.entity.CardData;
import shop.readmecorp.userserverreadme.modules.bootpay.entity.Metadata;

public interface CardDataRepository extends JpaRepository<CardData, Integer> {
}
