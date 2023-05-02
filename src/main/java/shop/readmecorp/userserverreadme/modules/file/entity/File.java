package shop.readmecorp.userserverreadme.modules.file.entity;

import lombok.*;
import org.hibernate.annotations.Comment;
import shop.readmecorp.userserverreadme.common.jpa.BaseTime;
import shop.readmecorp.userserverreadme.modules.file.dto.FileDTO;
import shop.readmecorp.userserverreadme.modules.file.enums.FileStatus;
import shop.readmecorp.userserverreadme.modules.file.response.FileResponse;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "FILE_TB")
public class File extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유번호")
    private Integer id;

    @Comment("파일 출처")
    @ManyToOne
    private FileInfo fileInfo;

    @Comment("파일 이름")
    private String fileName;

    @Comment("파일 경로")
    private String fileUrl;

    @Comment("파일 사이즈")
    private Long fileSize;

    @Comment("파일 확장자")
    private String extension;

    // Multi
    // File Insert
    // S3
    // File update url

    // 확장자 추가
//    jpg

    // 파일 크기
//    3MB

    @Comment("사진 활성화 상태")
    @Enumerated(EnumType.STRING)
    private FileStatus status;

    @Builder
    public File(Integer id, FileInfo fileInfo, String fileName, String fileUrl, Long fileSize, String extension, FileStatus status) {
        this.id = id;
        this.fileInfo = fileInfo;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.fileSize = fileSize;
        this.extension = extension;
        this.status = status;
    }

    public FileDTO toDTO() {
        return new FileDTO(id, fileName, fileUrl, status.name() );
    }

    public static List<FileDTO> toDTO(List<File> files) {
        return files.stream().map(File::toDTO)
                .collect(Collectors.toList());
    }

    public FileResponse toResponse() {
        return new FileResponse(id, fileInfo.toDTO(), fileName, fileUrl, status.name());
    }
}