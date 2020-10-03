package ch.hickmann.data.analysis.routes.processors;

import ch.hickmann.data.analysis.saga.services.SagaService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FileProcessor implements Processor  {

    private final Logger logger = LoggerFactory.getLogger(FileProcessor.class);
    private static final String CAMEL_ABSOLUTE_PATH = "CamelFileAbsolutePath";

    private final SagaService sagaService;

    public FileProcessor(SagaService sagaService) {
        this.sagaService = sagaService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        logger.info("New file found: {}", exchange.getMessage().getHeader(Exchange.FILE_NAME));
        sagaService.initialize(exchange.getMessage().getHeader(CAMEL_ABSOLUTE_PATH).toString());
    }
}
