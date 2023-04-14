package shop.readmecorp.userserverreadme.modules.file.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.modules.file.dto.FileInfoDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileUpdateRequest {

    private FileInfoDTO fileInfo;

    private String fileName;

    private String fileUrl;

    private String status;

}
