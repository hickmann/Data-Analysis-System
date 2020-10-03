package ch.hickmann.data.analysis.factory;

import ch.hickmann.data.analysis.domains.SoldItem;

import java.math.BigDecimal;

import static java.util.Objects.isNull;

public class SoldItemFactory {
    private static final String SOLD_ITEMS_FIELD_DELIMITER = "-";

    private static final int ITEM_ID_FIELD_POSITION = 0;
    private static final int QUANTITY_ITEM_FIELD_POSITION = 1;
    private static final int ITEM_PRICE_FIELD_POSITION = 2;

    public static SoldItem fromRaw(String rawItem) {
        String[] fields = rawItem.split(SOLD_ITEMS_FIELD_DELIMITER);

        return SoldItem.builder()
                .id(extractId(fields))
                .price(extractPrice(fields))
                .quantity(extractQuantity(fields))
                .build();
    }

    private static Long extractId(String[] fields) {
        return !isNull(fields[ITEM_ID_FIELD_POSITION])?Long.valueOf(fields[ITEM_ID_FIELD_POSITION]):null;
    }

    private static BigDecimal extractPrice(String[] fields) {
        return !isNull(fields[ITEM_PRICE_FIELD_POSITION]) ? new BigDecimal(fields[ITEM_PRICE_FIELD_POSITION]) : null;
    }

    private static Integer extractQuantity(String[] fields) {
        return !isNull(fields[QUANTITY_ITEM_FIELD_POSITION])?Integer.valueOf(fields[QUANTITY_ITEM_FIELD_POSITION]):null;
    }
}
