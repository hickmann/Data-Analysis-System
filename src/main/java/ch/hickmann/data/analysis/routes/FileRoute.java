package ch.hickmann.data.analysis.routes;

import ch.hickmann.data.analysis.routes.processors.FileProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileRoute extends RouteBuilder {

    @Value("${camelUri}")
    private String CAMEL_URI;

    @Value("${readingPath}")
    private String READING_PATH;

    private final FileProcessor fileProcessor;

    public FileRoute(FileProcessor fileProcessor) {
        this.fileProcessor = fileProcessor;
    }

    @Override
    public void configure() {
        from(getCamelUriComplete()).process(fileProcessor);
    }

    private String getCamelUriComplete() {
        String FULL_PATH = System.getProperty("user.home").concat(READING_PATH);
        return String.format(CAMEL_URI,FULL_PATH);
    }
}
