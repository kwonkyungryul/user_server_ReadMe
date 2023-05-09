package shop.readmecorp.userserverreadme.modules.banner.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.modules.file.dto.FileDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BannerDTO {

    private Integer id;

    private FileDTO imageFile;
}
