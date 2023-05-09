package shop.readmecorp.userserverreadme.modules.banner.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.readmecorp.userserverreadme.common.dto.ResponseDTO;
import shop.readmecorp.userserverreadme.modules.banner.dto.BannerDTO;
import shop.readmecorp.userserverreadme.modules.banner.service.BannerService;

import java.util.List;

@RestController
@RequestMapping("/banners")
public class BannerController {
    private final BannerService bannerService;

    public BannerController(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<BannerDTO>>> getList() {
        return ResponseEntity.ok(new ResponseDTO<>(1, "배너 조회가 완료되었습니다.", bannerService.getList()));
    }
}
