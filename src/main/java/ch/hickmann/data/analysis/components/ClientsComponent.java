package ch.hickmann.data.analysis.components;

import ch.hickmann.data.analysis.domains.Client;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class ClientsComponent extends ProcessComponent {

    private static final Integer CNPJ_FIELD_POSITION = 1;
    private static final Integer NAME_FIELD_POSITION = 2;
    private static final Integer BUSINESS_AREA_FIELD_POSITION = 3;

    private static final String CLIENT_DELIMITER = "ç";

    public Client processClientLine(String line) {
        String[] fields = getFields(line, CLIENT_DELIMITER);
        return Client.builder()
                .cnpj(extractCnpj(fields))
                .businessArea(extractBusinessArea(fields))
                .name(extractName(fields))
                .build();
    }

    private String extractName(String[] fields) {
        return !isNull(fields[NAME_FIELD_POSITION]) ? fields[NAME_FIELD_POSITION] : null;
    }

    private String extractBusinessArea(String[] fields) {
        return !isNull(fields[BUSINESS_AREA_FIELD_POSITION]) ? fields[BUSINESS_AREA_FIELD_POSITION] : null;
    }

    private String extractCnpj(String[] fields) {
        return !isNull(fields[CNPJ_FIELD_POSITION]) ? fields[CNPJ_FIELD_POSITION] : null;
    }
}
