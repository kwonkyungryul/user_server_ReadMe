package shop.readmecorp.userserverreadme.modules.file.service;

import org.springframework.stereotype.Service;
import shop.readmecorp.userserverreadme.modules.file.repository.FileRepository;

@Service
public class FileService {

    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }
}
