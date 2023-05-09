package shop.readmecorp.userserverreadme.modules.banner.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.readmecorp.userserverreadme.modules.banner.dto.BannerDTO;
import shop.readmecorp.userserverreadme.modules.banner.entity.Banner;
import shop.readmecorp.userserverreadme.modules.banner.enums.BannerStatus;
import shop.readmecorp.userserverreadme.modules.banner.repository.BannerRepository;
import shop.readmecorp.userserverreadme.modules.file.entity.File;
import shop.readmecorp.userserverreadme.modules.file.repository.FileRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class BannerService {
    private final BannerRepository bannerRepository;

    private final FileRepository fileRepository;

    public BannerService(BannerRepository bannerRepository, FileRepository fileRepository) {
        this.bannerRepository = bannerRepository;
        this.fileRepository = fileRepository;
    }

    public List<BannerDTO> getList() {
        List<Banner> bannerList = bannerRepository.findByStatus(BannerStatus.ACTIVE);
        return bannerList.stream().map(banner -> {
            BannerDTO bannerDTO = banner.toDTO();
            List<File> files = fileRepository.findByFileInfo_Id(banner.getImage().getId());
            bannerDTO.setImageFile(files.get(0).toDTO());
            return bannerDTO;
        }).collect(Collectors.toList());

    }
}
