package components;

import ch.hickmann.data.analysis.components.SalesComponent;
import ch.hickmann.data.analysis.components.SoldItemsComponent;
import ch.hickmann.data.analysis.domains.Sale;
import fixtures.SaleFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

    @Test
    public void shouldThrowErrorForNullLine() {
        //GIVEN
        SoldItemsComponent soldItemsComponent = new SoldItemsComponent();
        SalesComponent salesComponent = new SalesComponent(soldItemsComponent);

        String nullLine = null;

        //WHEN-THEN
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            salesComponent.processSaleLine(nullLine);
        }, "Linha não deve ser null.");
    }

    @Test
    public void shouldReturnNullInCorrectLineObject() {

        //GIVEN
        SoldItemsComponent soldItemsComponent = new SoldItemsComponent();
        SalesComponent salesComponent = new SalesComponent(soldItemsComponent);

        String saleLine = "00310[2-30-2.50,3-40-3.10]çPedro";

        //WHEN-THEN
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            salesComponent.processSaleLine(saleLine);
        }, "Linha deveria conter 3 parâmetros válidos.");
    }
}
