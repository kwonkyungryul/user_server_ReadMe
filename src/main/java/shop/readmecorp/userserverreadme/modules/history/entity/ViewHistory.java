package shop.readmecorp.userserverreadme.modules.history.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import shop.readmecorp.userserverreadme.common.jpa.BaseTime;
import shop.readmecorp.userserverreadme.modules.book.entity.Book;
import shop.readmecorp.userserverreadme.modules.history.dto.ViewHistoryDTO;
import shop.readmecorp.userserverreadme.modules.history.enums.HistoryStatus;
import shop.readmecorp.userserverreadme.modules.history.response.ViewHistoryResponse;
import shop.readmecorp.userserverreadme.modules.user.entity.User;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "VIEW_HISTORY_TB")
public class ViewHistory extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유번호")
    private Integer id;

    @Comment("유저")
    @ManyToOne
    private User user;

    @Comment("책")
    @ManyToOne
    private Book book;

    @Comment("마지막으로 본 페이지")
    private Integer lastPageNum;

    @Comment("최근 본 책 활성화 상태")
    @Enumerated(EnumType.STRING)
    private HistoryStatus status;


    @Builder
    public ViewHistory(Integer id, User user, Book book, Integer lastPageNum, HistoryStatus status) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.lastPageNum = lastPageNum;
        this.status = status;
    }

    public ViewHistoryDTO toDTO() {
        return new ViewHistoryDTO(id, user.toDTO(), book.toDTO(), lastPageNum, status.name());
    }

    public ViewHistoryResponse toResponse() {
        return new ViewHistoryResponse(id, user.toDTO(), book.toDTO(), lastPageNum, status.name());
    }
}


