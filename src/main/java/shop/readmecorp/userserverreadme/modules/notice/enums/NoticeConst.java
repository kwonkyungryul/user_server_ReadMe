package shop.readmecorp.userserverreadme.modules.notice.enums;

import shop.readmecorp.userserverreadme.modules.file.dto.FileDTO;

public interface NoticeConst {
    String notFound = "공지사항이 존재하지 않습니다.";
    FileDTO defaultNoticeFileDTO = new FileDTO(0, "존재하지 않는 파일", "");
}
