package shop.readmecorp.userserverreadme.modules.file.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.readmecorp.userserverreadme.modules.file.entity.File;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Integer> {

    File findByFileInfo_Id(Integer fileInfo_id);
}
