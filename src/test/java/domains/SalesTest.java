package domains;

import Fixtures.SoldItemsFixture;
import ch.hickmann.data.analysis.domains.Sale;
import ch.hickmann.data.analysis.domains.SoldItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

public class SalesTest {


    @Test
    public void shouldReturnRightTotalValue() {
        //GIVEN
        List<SoldItem> soldItemList = SoldItemsFixture.generateSoldItemsList(2);
        Sale sale = Sale.builder()
                .items(soldItemList)
                .build();

        BigDecimal expectedValue = new BigDecimal("80");

        //WHEN
        BigDecimal generatedValue = sale.totalValue();

        //THEN
        Assertions.assertEquals(generatedValue, expectedValue);
    }
}
