package ch.hickmann.data.analysis.components;

import ch.hickmann.data.analysis.domains.Sale;
import ch.hickmann.data.analysis.domains.SoldItem;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Objects.isNull;

@Component
public class SalesComponent extends ProcessComponent {
    private static final int SALE_ID_FIELD_POSITION = 1;
    private static final int SOLD_ITEMS_FIELD_POSITION = 2;
    private static final int SELLER_NAME_FIELD_POSITION = 3;

    private final String SELLER_DELIMITER = "ç";

    private final SoldItemsComponent soldItemsComponent;

    public SalesComponent(SoldItemsComponent soldItemsComponent) {
        this.soldItemsComponent = soldItemsComponent;
    }

    public Sale processSaleLine(String line) {
        String[] fields = getFields(line, SELLER_DELIMITER);

        return Sale.builder()
                .id(extractId(fields))
                .sellerName(extractSellerName(fields))
                .items(extractSoldItems(fields))
                .build();
    }

    private List<SoldItem> extractSoldItems(String[] fields) {
        return !isNull(fields[SOLD_ITEMS_FIELD_POSITION])?soldItemsComponent.extractSoldItems(fields[SOLD_ITEMS_FIELD_POSITION].replace("[","").replace("]","")):null;
    }

    private Long extractId(String[] fields) {
        return !isNull(fields[SALE_ID_FIELD_POSITION])?Long.valueOf(fields[SALE_ID_FIELD_POSITION]):null;
    }

    private String extractSellerName(String[] fields) {
        return !isNull(fields[SELLER_NAME_FIELD_POSITION])?fields[SELLER_NAME_FIELD_POSITION]:null;
    }

}
