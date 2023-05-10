package shop.readmecorp.userserverreadme.modules.banner.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import shop.readmecorp.userserverreadme.modules.common.jpa.BaseTime;
import shop.readmecorp.userserverreadme.modules.banner.dto.BannerDTO;
import shop.readmecorp.userserverreadme.modules.banner.enums.BannerStatus;
import shop.readmecorp.userserverreadme.modules.file.entity.FileInfo;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "BANNER_TB")
public class Banner extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유번호")
    private Integer id;

    @Comment("배너 이미지")
    @ManyToOne
    private FileInfo image;

    @Comment("배너 상태")
    @Enumerated(value = EnumType.STRING)
    private BannerStatus status;

    @Builder
    public Banner(Integer id, FileInfo image, BannerStatus status) {
        this.id = id;
        this.image = image;
        this.status = status;
    }

    public BannerDTO toDTO() {
        return new BannerDTO(id, null);
    }
}
