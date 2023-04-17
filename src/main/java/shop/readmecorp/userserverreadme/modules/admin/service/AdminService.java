package shop.readmecorp.userserverreadme.modules.admin.service;

import org.springframework.stereotype.Service;
import shop.readmecorp.userserverreadme.modules.admin.repository.AdminRepository;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
}
