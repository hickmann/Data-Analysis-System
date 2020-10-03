package ch.hickmann.data.analysis.saga.entities;

import ch.hickmann.data.analysis.saga.enumerations.SagaStatusEnum;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class SagaEntity {
    @Id
    @GeneratedValue
    private Long id;

    @CreationTimestamp
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    @Column(unique = true)
    private String path;

    private SagaStatusEnum status;

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "SagaEntity{" +
                "id=" + id +
                ", createDateTime=" + createDateTime +
                ", updateDateTime=" + updateDateTime +
                ", path='" + path + '\'' +
                ", status=" + status +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public SagaStatusEnum getStatus() {
        return status;
    }

    public void setStatus(SagaStatusEnum status) {
        this.status = status;
    }

    public static final class Builder {
        private Long id;
        private LocalDateTime createDateTime;
        private LocalDateTime updateDateTime;
        private String path;
        private SagaStatusEnum status;

        private Builder() {
        }

        public static Builder aSagaEntity() {
            return new Builder();
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder createDateTime(LocalDateTime createDateTime) {
            this.createDateTime = createDateTime;
            return this;
        }

        public Builder updateDateTime(LocalDateTime updateDateTime) {
            this.updateDateTime = updateDateTime;
            return this;
        }

        public Builder path(String path) {
            this.path = path;
            return this;
        }

        public Builder status(SagaStatusEnum status) {
            this.status = status;
            return this;
        }

        public SagaEntity build() {
            SagaEntity sagaEntity = new SagaEntity();
            sagaEntity.setId(id);
            sagaEntity.setCreateDateTime(createDateTime);
            sagaEntity.setUpdateDateTime(updateDateTime);
            sagaEntity.setPath(path);
            sagaEntity.setStatus(status);
            return sagaEntity;
        }
    }
}
