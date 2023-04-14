package shop.readmecorp.userserverreadme.modules.file.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.readmecorp.userserverreadme.modules.file.entity.File;

public interface FileRepository extends JpaRepository<File, Integer> {
}
