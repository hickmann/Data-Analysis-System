package ch.hickmann.data.analysis.services;

import ch.hickmann.data.analysis.components.FilesComponent;
import ch.hickmann.data.analysis.domains.ProcessedFile;
import ch.hickmann.data.analysis.domains.Report;
import ch.hickmann.data.analysis.saga.services.SagaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ReportsService {

    private static final Logger logger = LoggerFactory.getLogger(ReportsService.class);

    @Value("${writingPath}")
    private String WRITING_PATH;

    @Value("${doneExtension}")
    private String DONE_EXTENSION;

    private static final Pattern extPattern = Pattern.compile("(?<=.)\\.[^.]+$");

    private final FilesComponent filesComponent;
    private final SagaService sagaService;

    public ReportsService(FilesComponent filesComponent, SagaService sagaService) {
        this.filesComponent = filesComponent;
        this.sagaService = sagaService;
    }


    public void generateReport(ProcessedFile processedFile, String path) {
        logger.info("Generating Report for {}", path);
        Report report = new Report(processedFile,path);
        logger.info("Report Generated.");
        logger.info("Saving File {}", report);
        saveReportFile(report);
        logger.info("File saved.");
    }

    private void saveReportFile(Report report) {
        try {
            filesComponent.saveReport(createReportPath(report), report.toStringFormatted());
        } catch (IOException e) {
            logger.info("Error saving Report - {}", report);
            sagaService.error(report.getPath());
        }
    }

    private String createReportPath(Report report) {
        String fileName = extPattern.matcher(Paths.get(report.getPath()).getFileName().toString()).replaceAll("");
        String fullPath = System.getProperty("user.home").concat(WRITING_PATH);
        return new StringBuilder().append(fullPath)
                .append("\\")
                .append(fileName)
                .append(DONE_EXTENSION)
                .toString();
    }
}
