package shop.readmecorp.userserverreadme.modules.notice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import shop.readmecorp.userserverreadme.modules.notice.entity.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {

}
