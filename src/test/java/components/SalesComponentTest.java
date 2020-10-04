package components;

import Fixtures.SaleFixture;
import ch.hickmann.data.analysis.components.SalesComponent;
import ch.hickmann.data.analysis.components.SoldItemsComponent;
import ch.hickmann.data.analysis.domains.Client;
import ch.hickmann.data.analysis.domains.Sale;
import ch.hickmann.data.analysis.domains.SoldItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SalesComponentTest {

    @Test
    public void shouldCreateCorrectObject() {
        //GIVEN
        SoldItemsComponent soldItemsComponent = new SoldItemsComponent();
        SalesComponent salesComponent = new SalesComponent(soldItemsComponent);

        String saleLine = "003ç10ç[2-30-2.50,3-40-3.10]çPedro";

        Sale expected = SaleFixture.generateSale();

        //WHEN
        Sale generated = salesComponent.processSaleLine(saleLine);

        //THEN
        Assertions.assertEquals(generated.toString(), expected.toString());
    }
}
