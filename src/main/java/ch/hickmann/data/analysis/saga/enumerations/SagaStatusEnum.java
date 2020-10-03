package ch.hickmann.data.analysis.saga.enumerations;

public enum SagaStatusEnum {

    FILE_READ("FILE_READ"),
    FILE_PROCESSED("FILE_PROCESSED"),
    ERROR("ERROR"),
    COMPLETE("COMPLETE");

    private String status;

    SagaStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
