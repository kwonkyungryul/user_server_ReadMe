package shop.readmecorp.userserverreadme.modules.file.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.readmecorp.userserverreadme.modules.file.dto.FileInfoDTO;
import shop.readmecorp.userserverreadme.modules.file.entity.FileInfo;
import shop.readmecorp.userserverreadme.modules.file.enums.FileType;
import shop.readmecorp.userserverreadme.modules.file.repository.FileInfoRepository;
import shop.readmecorp.userserverreadme.modules.user.entity.User;
import shop.readmecorp.userserverreadme.modules.user.request.UserSaveRequest;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileInfoService {

    private final FileInfoRepository fileInfoRepository;

    public FileInfoService(FileInfoRepository fileInfoRepository) {
        this.fileInfoRepository = fileInfoRepository;
    }


    public Optional<FileInfoDTO> getFileInfo(Integer id) {
        return fileInfoRepository.findById(id).map(FileInfo::toDTO);
    }

    @Transactional
    public FileInfo save(FileType fileType){
        FileInfo fileInfo = FileInfo.builder()
                .type(fileType).build();

        return fileInfoRepository.save(fileInfo);

    }
}
