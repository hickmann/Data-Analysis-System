package ch.hickmann.data.analysis.services;

import ch.hickmann.data.analysis.components.FilesComponent;
import ch.hickmann.data.analysis.domains.ProcessedFile;
import ch.hickmann.data.analysis.saga.services.SagaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FilesService {

    private static final Logger logger = LoggerFactory.getLogger(FilesService.class);

    private final SagaService sagaService;
    private final ReportsService reportsService;
    private final FilesComponent filesComponent;

    @Value("${readingPath}")
    private String READING_PATH;

    public FilesService(SagaService sagaService, ReportsService reportsService, FilesComponent filesComponent) {
        this.sagaService = sagaService;
        this.reportsService = reportsService;
        this.filesComponent = filesComponent;
    }

    public void startFilesService() {
        String FULL_PATH = System.getProperty("user.home").concat(READING_PATH);

        logger.info("Starting Files Service - {}", FULL_PATH);
        try {
            Files.list(Paths.get(FULL_PATH))
                    .filter(file -> isDatFile(file))
                    .forEach(file -> sagaService.initialize(file.toString()));
        } catch (IOException e) {
            logger.error("It was not possible to find path: {}", FULL_PATH);
        }
    }

    private boolean isDatFile(Path file) {
        return file.toString().endsWith(".dat");
    }

    public void startProcessingFile(String path) {
        ProcessedFile processedFile = filesComponent.process(path);

        reportsService.generateReport(processedFile, path);

        sagaService.processed(path);
    }

    public void completeProcessingFile(String path) {
        sagaService.complete(path);
    }
}
