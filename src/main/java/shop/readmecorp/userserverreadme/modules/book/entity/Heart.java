package shop.readmecorp.userserverreadme.modules.book.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import shop.readmecorp.userserverreadme.modules.common.jpa.BaseTime;
import shop.readmecorp.userserverreadme.modules.book.dto.HeartDTO;
import shop.readmecorp.userserverreadme.modules.book.enums.HeartStatus;
import shop.readmecorp.userserverreadme.modules.book.response.HeartResponse;
import shop.readmecorp.userserverreadme.modules.user.entity.User;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "HEART_TB")
public class Heart extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유번호")
    private Integer id;

    @Comment("하트 누른 유저")
    @ManyToOne
    private User user;

    @Comment("하트 눌린 책")
    @ManyToOne
    private Book book;

    @Comment("하트 활성화 상태")
    @Enumerated(EnumType.STRING)
    private HeartStatus status;

    @Builder
    public Heart(Integer id, User user, Book book, HeartStatus status) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.status = status;
    }

    public HeartDTO toDTO() {
        return new HeartDTO(id, user.toDTO(), book.toDTO(),  status.name()  );
    }

    public HeartResponse toResponse() {
        return new HeartResponse(id, user.toDTO(), book.toDTO(),  status.name());
    }
}
