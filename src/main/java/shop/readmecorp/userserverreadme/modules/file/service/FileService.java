package shop.readmecorp.userserverreadme.modules.file.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.readmecorp.userserverreadme.modules.file.dto.FileDTO;
import shop.readmecorp.userserverreadme.modules.file.dto.FileInfoDTO;
import shop.readmecorp.userserverreadme.modules.file.entity.File;
import shop.readmecorp.userserverreadme.modules.file.entity.FileInfo;
import shop.readmecorp.userserverreadme.modules.file.enums.FileStatus;
import shop.readmecorp.userserverreadme.modules.file.enums.FileType;
import shop.readmecorp.userserverreadme.modules.file.repository.FileRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FileService {

    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public Optional<FileDTO> getFile(Integer id) {
        List<File> files = fileRepository.findByFileInfo_Id(id);
        if (files.size() == 0) {
            return Optional.empty();
        }
        return Optional.of(files.get(0).toDTO());
    }

    public List<FileDTO> getFiles(Integer id) {
        return fileRepository.findByFileInfo_Id(id).stream().map(File::toDTO).collect(Collectors.toList());
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
