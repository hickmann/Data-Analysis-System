package ch.hickmann.data.analysis.components;

import ch.hickmann.data.analysis.domains.Seller;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.math.BigDecimal;

import static java.util.Objects.isNull;

@Component
public class SellersComponent extends ProcessComponent {

    private static final Integer CPF_FIELD_POSITION = 1;
    private static final Integer NAME_FIELD_POSITION = 2;
    private static final Integer WAGE_FIELD_POSITION = 3;
    private final String SELLER_DELIMITER = "ç";

    public Seller processSellerLine(String line) {
        Assert.notNull(line, "Linha não deve ser null.");
        String[] fields = getFields(line, SELLER_DELIMITER);
        Assert.isTrue((fields.length == 3), "Linha deveria conter 3 parâmetros válidos.");

        return Seller.builder()
                .cpf(extractCPF(fields))
                .name(extractName(fields))
                .wage(extractWage(fields))
                .build();
    }

    private BigDecimal extractWage(String[] fields) {
        return !isNull(fields[WAGE_FIELD_POSITION]) ? new BigDecimal(fields[WAGE_FIELD_POSITION]) : null;
    }

    private String extractName(String[] fields) {
        return !isNull(fields[NAME_FIELD_POSITION]) ? fields[NAME_FIELD_POSITION] : null;
    }

    private String extractCPF(String[] fields) {
        return !isNull(fields[CPF_FIELD_POSITION]) ? fields[CPF_FIELD_POSITION] : null;
    }

}
