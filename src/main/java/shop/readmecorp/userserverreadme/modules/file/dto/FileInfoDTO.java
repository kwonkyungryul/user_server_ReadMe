package shop.readmecorp.userserverreadme.modules.file.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.modules.file.entity.FileInfo;
import shop.readmecorp.userserverreadme.modules.file.enums.FileType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileInfoDTO {

    private Integer id;

    private String type;

    public FileInfo toEntity() {
        return FileInfo.builder()
                .id(id)
                .type(FileType.valueOf(type))
                .build();
    }
}
