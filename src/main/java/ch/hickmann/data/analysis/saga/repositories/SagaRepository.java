package ch.hickmann.data.analysis.saga.repositories;

import ch.hickmann.data.analysis.saga.entities.SagaEntity;
import ch.hickmann.data.analysis.saga.enumerations.SagaStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SagaRepository extends JpaRepository<SagaEntity, Long> {
    SagaEntity findByPath(String path);

    SagaEntity findByPathAndStatus(String path, SagaStatusEnum status);
}
