package shop.readmecorp.userserverreadme.modules.history.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import shop.readmecorp.userserverreadme.common.jpa.BaseTime;
import shop.readmecorp.userserverreadme.modules.history.dto.SearchHistoryDTO;
import shop.readmecorp.userserverreadme.modules.history.enums.HistoryStatus;
import shop.readmecorp.userserverreadme.modules.history.response.SearchHistoryResponse;
import shop.readmecorp.userserverreadme.modules.user.entity.User;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "SEARCH_HISTORY_TB")
public class SearchHistory extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유번호")
    private Integer id;

    @Comment("유저")
    @ManyToOne
    private User user;

    @Comment("검색 내용")
    private String content;

    @Comment("검색기록 활성화 상태")
    @Enumerated(EnumType.STRING)
    private HistoryStatus status;

    @Builder
    public SearchHistory(Integer id, User user, String content, HistoryStatus status) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.status = status;
    }

    public SearchHistoryDTO toDTO() {
        return new SearchHistoryDTO(id, user.toDTO(), content, status.name()  );
    }

    public SearchHistoryResponse toResponse() {
        return new SearchHistoryResponse(id, user.toDTO(), content, status.name());
    }
}
