package ch.hickmann.data.analysis.domains.converter;

import ch.hickmann.data.analysis.saga.entities.SagaEntity;
import ch.hickmann.data.analysis.messages.domains.SagaMessage;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.Assert;

public class SagaEntityToSagaMessage implements Converter<SagaEntity, SagaMessage> {

    @Override
    public SagaMessage convert(SagaEntity sagaEntity) {
        Assert.notNull(sagaEntity, "SagaEntity is null.");

        return SagaMessage.builder()
                .id(sagaEntity.getId())
                .status(sagaEntity.getStatus())
                .path(sagaEntity.getPath())
                .build();
    }

}
