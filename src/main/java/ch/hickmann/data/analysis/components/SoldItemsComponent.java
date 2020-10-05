package ch.hickmann.data.analysis.components;

import ch.hickmann.data.analysis.domains.SoldItem;
import ch.hickmann.data.analysis.factorys.SoldItemFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SoldItemsComponent {
    private static final String SOLD_ITEMS_DELIMITER = ",";

    public List<SoldItem> extractSoldItems(String soldItemsStr) {
        return Arrays.stream(soldItemsStr.split(SOLD_ITEMS_DELIMITER))
                .map(SoldItemFactory::fromRaw)
                .collect(Collectors.toList());
    }
}
