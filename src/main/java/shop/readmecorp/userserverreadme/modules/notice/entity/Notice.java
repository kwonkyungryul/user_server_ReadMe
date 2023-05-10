package shop.readmecorp.userserverreadme.modules.notice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import shop.readmecorp.userserverreadme.modules.common.jpa.BaseTime;
import shop.readmecorp.userserverreadme.modules.file.entity.FileInfo;
import shop.readmecorp.userserverreadme.modules.notice.dto.NoticeDTO;
import shop.readmecorp.userserverreadme.modules.notice.enums.NoticeStatus;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "NOTICE_TB")
public class Notice extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유번호")
    private Integer id;

    @Comment("공지사항 제목")
    private String title;

    @Comment("공지사항 내용")
    private String content;

    @Comment("공지사항 이미지")
    @ManyToOne
    private FileInfo image;

    @Comment("공지사항 상태")
    @Enumerated(value = EnumType.STRING)
    private NoticeStatus status;

    public NoticeDTO toDTO() {
        return new NoticeDTO(id, title, content, null);
    }
}
