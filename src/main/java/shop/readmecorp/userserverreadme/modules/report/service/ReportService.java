package shop.readmecorp.userserverreadme.modules.report.service;

import org.springframework.stereotype.Service;
import shop.readmecorp.userserverreadme.modules.report.repository.ReportRepository;

@Service
public class ReportService {

    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }
}
