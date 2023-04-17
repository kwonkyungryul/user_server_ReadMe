package shop.readmecorp.userserverreadme.modules.file.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import shop.readmecorp.userserverreadme.common.jpa.BaseTime;
import shop.readmecorp.userserverreadme.modules.file.dto.FileInfoDTO;
import shop.readmecorp.userserverreadme.modules.file.enums.FileType;
import shop.readmecorp.userserverreadme.modules.file.response.FileInfoResponse;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FILE_INFO_LIST")
public class FileInfo extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유번호")
    private Integer id;

    @Comment("파일 출처")
    @Enumerated(EnumType.STRING)
    private FileType type;

    @Builder
    public FileInfo(FileType type) {
        this.type = type;
    }

    public FileInfoDTO toDTO() {
        return new FileInfoDTO(id, type.name());
    }

    public FileInfoResponse toResponse() {
        return new FileInfoResponse(id, type.name());
    }
}
