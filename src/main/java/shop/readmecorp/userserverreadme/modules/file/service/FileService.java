package shop.readmecorp.userserverreadme.modules.file.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.readmecorp.userserverreadme.modules.file.entity.File;
import shop.readmecorp.userserverreadme.modules.file.entity.FileInfo;
import shop.readmecorp.userserverreadme.modules.file.enums.FileStatus;
import shop.readmecorp.userserverreadme.modules.file.enums.FileType;
import shop.readmecorp.userserverreadme.modules.file.repository.FileRepository;

@Service
public class FileService {

    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Transactional
    public File save(FileInfo fileInfo){

        File file = File.builder()
                .fileInfo(fileInfo)
                .fileName("")
                .fileUrl("")
                .status(FileStatus.WAIT)
                .build();

        return fileRepository.save(file);

    }
}
