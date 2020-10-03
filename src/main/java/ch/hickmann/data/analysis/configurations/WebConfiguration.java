package ch.hickmann.data.analysis.configurations;

import ch.hickmann.data.analysis.domains.converter.SagaEntityToSagaMessage;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
 
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new SagaEntityToSagaMessage());
    }
}