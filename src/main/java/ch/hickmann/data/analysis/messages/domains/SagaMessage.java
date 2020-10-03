package ch.hickmann.data.analysis.messages.domains;

import ch.hickmann.data.analysis.saga.enumerations.SagaStatusEnum;

public class SagaMessage {
    private Long id;
    private String path;
    private SagaStatusEnum status;

    public static Builder builder() { return new Builder(); }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        private String path;
        private SagaStatusEnum status;

        private Builder() {
        }

        public static Builder aSagaMessage() {
            return new Builder();
        }

        public Builder id(Long id) {
            this.id = id;
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

        public SagaMessage build() {
            SagaMessage sagaMessage = new SagaMessage();
            sagaMessage.setId(id);
            sagaMessage.setPath(path);
            sagaMessage.setStatus(status);
            return sagaMessage;
        }
    }

    @Override
    public String toString() {
        return "SagaMessage{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", status=" + status +
                '}';
    }
}
