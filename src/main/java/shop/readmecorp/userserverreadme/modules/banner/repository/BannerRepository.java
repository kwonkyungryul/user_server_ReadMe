package shop.readmecorp.userserverreadme.modules.banner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.readmecorp.userserverreadme.modules.banner.entity.Banner;
import shop.readmecorp.userserverreadme.modules.banner.enums.BannerStatus;

import java.util.List;

public interface BannerRepository extends JpaRepository<Banner, Integer> {
    List<Banner> findByStatus(BannerStatus status);
}
