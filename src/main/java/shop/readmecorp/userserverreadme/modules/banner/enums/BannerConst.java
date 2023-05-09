package shop.readmecorp.userserverreadme.modules.banner.enums;

import shop.readmecorp.userserverreadme.modules.file.dto.FileDTO;

public interface BannerConst {
    String notFound = "배너가 존재하지 않습니다";
    FileDTO defaultBannerFileDTO = new FileDTO(0, "존재하지 않는 파일", "");

}
