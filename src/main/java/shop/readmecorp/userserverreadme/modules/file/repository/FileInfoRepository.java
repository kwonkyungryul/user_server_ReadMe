package shop.readmecorp.userserverreadme.modules.file.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.readmecorp.userserverreadme.modules.file.entity.FileInfo;

public interface FileInfoRepository extends JpaRepository<FileInfo, Integer> {
}
