package shop.readmecorp.userserverreadme.modules.notice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.modules.file.dto.FileDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDTO {

    private Integer id;

    private String title;

    private String content;

    private FileDTO imageFile;
}
