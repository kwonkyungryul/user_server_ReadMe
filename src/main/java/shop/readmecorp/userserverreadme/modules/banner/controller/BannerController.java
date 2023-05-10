package shop.readmecorp.userserverreadme.modules.banner.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.readmecorp.userserverreadme.modules.common.dto.ResponseDTO;
import shop.readmecorp.userserverreadme.modules.common.exception.Exception400;
import shop.readmecorp.userserverreadme.modules.banner.dto.BannerDTO;
import shop.readmecorp.userserverreadme.modules.banner.entity.Banner;
import shop.readmecorp.userserverreadme.modules.banner.enums.BannerConst;
import shop.readmecorp.userserverreadme.modules.banner.service.BannerService;
import shop.readmecorp.userserverreadme.modules.file.dto.FileDTO;
import shop.readmecorp.userserverreadme.modules.file.service.FileService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/banners")
public class BannerController {
    private final BannerService bannerService;

    private final FileService fileService;

    public BannerController(BannerService bannerService, FileService fileService) {
        this.bannerService = bannerService;
        this.fileService = fileService;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<BannerDTO>>> getList() {
        List<Banner> bannerList = bannerService.getList();
        if (bannerList.size() == 0) {
            throw new Exception400(BannerConst.notFound);
        }

        return ResponseEntity.ok(new ResponseDTO<>(1, "배너 조회가 완료되었습니다.",
                bannerList.stream().map(banner -> {
                    BannerDTO bannerDTO = banner.toDTO();
                    List<FileDTO> files = fileService.getFiles(banner.getImage().getId());
                    bannerDTO.setImageFile(files.get(0));
                    return bannerDTO;
                }).collect(Collectors.toList())));
    }
}
