package shop.readmecorp.userserverreadme.modules.notice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.readmecorp.userserverreadme.common.exception.Exception400;
import shop.readmecorp.userserverreadme.modules.file.entity.File;
import shop.readmecorp.userserverreadme.modules.file.repository.FileRepository;
import shop.readmecorp.userserverreadme.modules.notice.dto.NoticeDTO;
import shop.readmecorp.userserverreadme.modules.notice.entity.Notice;
import shop.readmecorp.userserverreadme.modules.notice.enums.NoticeConst;
import shop.readmecorp.userserverreadme.modules.notice.repository.NoticeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class NoticeService {
    private final NoticeRepository noticeRepository;

    private final FileRepository fileRepository;

    public NoticeService(NoticeRepository noticeRepository, FileRepository fileRepository) {
        this.noticeRepository = noticeRepository;
        this.fileRepository = fileRepository;
    }


    public Page<NoticeDTO> getList(Pageable pageable) {
        Page<Notice> page = noticeRepository.findAll(pageable);
        return page.map(notice -> {
            NoticeDTO noticeDTO = notice.toDTO();
            List<File> files = fileRepository.findByFileInfo_Id(notice.getImage().getId());
            if (files.size() == 0) {
                noticeDTO.setImageFile(NoticeConst.defaultBookFileDTO);
            }
            noticeDTO.setImageFile(files.get(0).toDTO());
            return noticeDTO;
        });
    }

    public Optional<Notice> getNotice(Integer id) {
        return noticeRepository.findById(id);
    }
}
