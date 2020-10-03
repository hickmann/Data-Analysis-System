package ch.hickmann.data.analysis.saga.services;

import ch.hickmann.data.analysis.messages.domains.SagaMessage;
import ch.hickmann.data.analysis.saga.entities.SagaEntity;
import ch.hickmann.data.analysis.saga.enumerations.SagaStatusEnum;
import ch.hickmann.data.analysis.saga.repositories.SagaRepository;
import ch.hickmann.data.analysis.services.IdempotenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.ConversionService;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class SagaService {

    private final Logger logger = LoggerFactory.getLogger(SagaService.class);
    private final SagaRepository sagaRepository;
    private final JmsTemplate jmsTemplate;
    private final ConversionService conversionService;
    private final IdempotenceService idempotenceService;

    @Value("${sagaQueue}")
    private String SAGA_QUEUE;

    public SagaService(SagaRepository sagaRepository, JmsTemplate jmsTemplate, ConversionService conversionService, IdempotenceService idempotenceService) {
        this.sagaRepository = sagaRepository;
        this.jmsTemplate = jmsTemplate;
        this.conversionService = conversionService;
        this.idempotenceService = idempotenceService;
    }

    public void initialize(String path) {
        if (idempotenceService.isUnique(path)) {
            logger.info("Starting Saga for file {}", path);
            SagaEntity sagaEntity = SagaEntity.builder()
                    .path(path)
                    .status(SagaStatusEnum.FILE_READ)
                    .build();

            saveAndSendSagaMessage(sagaEntity);
        } else {
            logger.info("Duplicated file found - {}", path);
        }
    }

    public void complete(String path) {
        SagaEntity sagaEntity = sagaRepository.findByPath(path);
        sagaEntity.setStatus(SagaStatusEnum.COMPLETE);

        saveAndSendSagaMessage(sagaEntity);

        logger.info("File Processing Completed - {}", path);
    }

    public void processed(String path) {
        SagaEntity sagaEntity = sagaRepository.findByPath(path);
        sagaEntity.setStatus(SagaStatusEnum.COMPLETE);

        saveAndSendSagaMessage(sagaEntity);

    }

    private void saveSaga(SagaEntity sagaEntity) {
        sagaRepository.save(sagaEntity);
        logger.info("Saga saved. - {}", sagaEntity);
    }

    private void sendSagaMessage(SagaEntity sagaEntity) {
        SagaMessage sagaMessage = conversionService.convert(sagaEntity, SagaMessage.class);
        jmsTemplate.convertAndSend(SAGA_QUEUE, sagaMessage);
        logger.info("Saga Message sent {}", sagaMessage);
    }

    private void saveAndSendSagaMessage(SagaEntity sagaEntity) {
        saveSaga(sagaEntity);
        sendSagaMessage(sagaEntity);
    }
}
