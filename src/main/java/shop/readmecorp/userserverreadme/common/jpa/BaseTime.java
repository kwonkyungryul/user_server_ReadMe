package shop.readmecorp.userserverreadme.common.jpa;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.Objects;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTime {
    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void changeCreatedDate(LocalDateTime now) {
        createdDate = Objects.requireNonNullElseGet(now, LocalDateTime::now);
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }
}
