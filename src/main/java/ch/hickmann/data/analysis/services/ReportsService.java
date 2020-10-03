package ch.hickmann.data.analysis.services;

import ch.hickmann.data.analysis.domains.ProcessedFile;
import ch.hickmann.data.analysis.domains.Report;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ReportsService {

    private static final Logger logger = LoggerFactory.getLogger(ReportsService.class);

    public void generateReport(ProcessedFile processedFile, String path) {
        logger.info("Generating Report for {}", path);
        Report report = new Report(processedFile,path);
        logger.info("Report Generated {}", report);
    }
}
