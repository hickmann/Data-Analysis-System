package ch.hickmann.data.analysis.services;

import ch.hickmann.data.analysis.domains.ProcessedFile;
import ch.hickmann.data.analysis.domains.Report;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ReportsService {

    private static final Logger logger = LogManager.getLogger(ReportsService.class);

    public void generateReport(ProcessedFile processedFile, String path) {
        logger.info("Generating Report for {}", path);
        Report report = new Report(processedFile,path);
        logger.info("Report Generated {}", report);
    }
}
