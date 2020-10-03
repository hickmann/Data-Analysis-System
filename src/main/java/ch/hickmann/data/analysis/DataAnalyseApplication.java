package ch.hickmann.data.analysis;

import ch.hickmann.data.analysis.services.FilesService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
public class DataAnalyseApplication implements CommandLineRunner {
    private final FilesService filesService;

    public DataAnalyseApplication(FilesService filesService) {
        this.filesService = filesService;
    }

    public static void main(String[] args) {
        SpringApplication.run(DataAnalyseApplication.class, args);
    }

    @Override
    public void run(String... args) {
        filesService.startFilesService();
    }
}
