package shop.readmecorp.userserverreadme.modules.notice.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.readmecorp.userserverreadme.common.dto.ResponseDTO;
import shop.readmecorp.userserverreadme.common.exception.Exception400;
import shop.readmecorp.userserverreadme.modules.file.dto.FileDTO;
import shop.readmecorp.userserverreadme.modules.file.service.FileService;
import shop.readmecorp.userserverreadme.modules.notice.dto.NoticeDTO;
import shop.readmecorp.userserverreadme.modules.notice.entity.Notice;
import shop.readmecorp.userserverreadme.modules.notice.enums.NoticeConst;
import shop.readmecorp.userserverreadme.modules.notice.service.NoticeService;

import java.util.Optional;

@RestController
@RequestMapping("/notices")
public class NoticeController {
    private final NoticeService noticeService;

    private final FileService fileService;

    public NoticeController(NoticeService noticeService, FileService fileService) {
        this.noticeService = noticeService;
        this.fileService = fileService;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<Page<NoticeDTO>>> getList(Pageable pageable) {
        return ResponseEntity.ok(new ResponseDTO<>(1, "공지사항 조회 완료되었습니다.", noticeService.getList(pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<NoticeDTO>> getNotice(@PathVariable Integer id) {
        Optional<Notice> optionalNotice = noticeService.getNotice(id);
        if (optionalNotice.isEmpty()) {
            throw new Exception400(NoticeConst.notFound);
        }
        Notice notice = optionalNotice.get();
        NoticeDTO noticeDTO = notice.toDTO();
        Optional<FileDTO> optionalFileDTO = fileService.getFile(notice.getImage().getId());
        if (optionalFileDTO.isEmpty()) {
            noticeDTO.setImageFile(NoticeConst.defaultNoticeFileDTO);
        }
        noticeDTO.setImageFile(optionalFileDTO.get());
        return ResponseEntity.ok(new ResponseDTO<>(1, "공지사항 단건 조회 완료되었습니다.", noticeDTO));
    }
}
