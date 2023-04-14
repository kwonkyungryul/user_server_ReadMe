package shop.readmecorp.userserverreadme.modules.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.readmecorp.userserverreadme.modules.report.entity.Report;

public interface ReportRepository extends JpaRepository<Report, Integer> {
}
