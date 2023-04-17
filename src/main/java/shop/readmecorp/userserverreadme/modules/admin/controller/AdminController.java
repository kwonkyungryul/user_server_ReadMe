package shop.readmecorp.userserverreadme.modules.admin.controller;

import org.springframework.web.bind.annotation.RestController;
import shop.readmecorp.userserverreadme.modules.admin.service.AdminService;

@RestController
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
}
