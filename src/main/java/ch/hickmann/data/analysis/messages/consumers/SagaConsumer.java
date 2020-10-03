package ch.hickmann.data.analysis.messages.consumers;

import ch.hickmann.data.analysis.domains.exceptions.BusinessException;
import ch.hickmann.data.analysis.messages.domains.SagaMessage;
import ch.hickmann.data.analysis.services.FilesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class SagaConsumer {

    private static final Logger logger =  LoggerFactory.getLogger(SagaConsumer.class);

    private final FilesService filesService;

    public SagaConsumer(FilesService filesService) {
        this.filesService = filesService;
    }

    @JmsListener(destination = "${sagaQueue}", containerFactory = "defaultContainerFactory")
    public void receiveMessage(@Payload SagaMessage saga) {
        logger.info("New Saga Message Received - {}", saga);

        switch (saga.getStatus()) {
            case FILE_READ:
                filesService.startProcessingFile(saga.getPath());
                break;
            case FILE_PROCESSED:
                filesService.completeProcessingFile(saga.getPath());
                break;
            case ERROR:
                break;
            case COMPLETE:
                break;
            default:
                logger.error("Saga Status not recognized - {}", saga);
                throw new BusinessException("Saga Status not recognized.");
        }
    }

}

