package ch.hickmann.data.analysis.services;

import ch.hickmann.data.analysis.messages.domains.SagaMessage;
import ch.hickmann.data.analysis.saga.entities.SagaEntity;
import ch.hickmann.data.analysis.saga.repositories.SagaRepository;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class IdempotenceService {

    private final SagaRepository sagaRepository;

    public IdempotenceService(SagaRepository sagaRepository) {
        this.sagaRepository = sagaRepository;
    }

    public boolean isUnique(String path) {
        SagaEntity sagaEntity = sagaRepository.findByPath(path);
        return isNull(sagaEntity);
    }
}
