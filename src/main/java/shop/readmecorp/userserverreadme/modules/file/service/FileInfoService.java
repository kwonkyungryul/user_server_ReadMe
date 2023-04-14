package shop.readmecorp.userserverreadme.modules.file.service;

import org.springframework.stereotype.Service;
import shop.readmecorp.userserverreadme.modules.file.repository.FileInfoRepository;

@Service
public class FileInfoService {

    private final FileInfoRepository fileInfoRepository;

    public FileInfoService(FileInfoRepository fileInfoRepository) {
        this.fileInfoRepository = fileInfoRepository;
    }
}
