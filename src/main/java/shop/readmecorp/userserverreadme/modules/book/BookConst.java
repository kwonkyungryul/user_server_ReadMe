package shop.readmecorp.userserverreadme.modules.book;

import shop.readmecorp.userserverreadme.modules.file.dto.FileDTO;

public interface BookConst {

    String notFound = "요청한 도서가 존재하지 않습니다.";
    FileDTO defaultBookFileDTO = new FileDTO(0, "존재하지 않는 파일", "");
}
