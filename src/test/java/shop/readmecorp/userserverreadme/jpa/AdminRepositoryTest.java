package shop.readmecorp.userserverreadme.jpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import shop.readmecorp.userserverreadme.common.jpa.RoleType;
import shop.readmecorp.userserverreadme.modules.admin.entity.Admin;
import shop.readmecorp.userserverreadme.modules.admin.enums.AdminStatus;
import shop.readmecorp.userserverreadme.modules.admin.repository.AdminRepository;

import java.util.Optional;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@DisplayName("ADMIN JPA 테스트")
public class AdminRepositoryTest {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @Transactional
    void selectAll() {
        var admins = adminRepository.findAll();
        Assertions.assertNotEquals(admins.size(), 0);

        Admin admin = admins.get(0);
        Assertions.assertEquals(admin.getUsername(), "어드민이름");
    }

    @Test
    @Transactional
    void selectAndUpdate() {
        var optionalAdmins = this.adminRepository.findById(1);

        if(optionalAdmins.isPresent()) {
            var result = optionalAdmins.get();
            Assertions.assertEquals(result.getUsername(),"어드민이름");

            var status = AdminStatus.WAIT;
            result.setStatus(status);
            Admin merge = entityManager.merge(result);

            Assertions.assertEquals(merge.getStatus(),AdminStatus.WAIT);
        } else {
            Assertions.assertNotNull(optionalAdmins.get());
        }
    }

    @Test
    @Transactional
    void insertAndDelete() {
        Admin admin = setUp("어드민이름2", "1111", RoleType.ADMIN, AdminStatus.ACTIVE);
        Optional<Admin> findAdmin = this.adminRepository.findById(admin.getId());

        if(findAdmin.isPresent()) {
            var result = findAdmin.get();
            Assertions.assertEquals(result.getUsername(), "어드민이름2");
            entityManager.remove(admin);
            Optional<Admin> deleteAdmin = this.adminRepository.findById(admin.getId());
            if (deleteAdmin.isPresent()) {
                Assertions.assertNull(deleteAdmin.get());
            }
        } else {
            Assertions.assertNotNull(findAdmin.get());
        }
    }

    private Admin setUp(String username, String password, RoleType role, AdminStatus status) {
        var admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        admin.setRole(role);
        admin.setStatus(status);
        return this.entityManager.persist(admin);
    }
}
