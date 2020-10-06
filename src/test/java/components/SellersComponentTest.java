package components;

import ch.hickmann.data.analysis.components.SellersComponent;
import ch.hickmann.data.analysis.domains.Seller;
import fixtures.SellerFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SellersComponentTest {

    @Test
    public void shouldCreateCorrectObject() {

        SellersComponent sellersComponent = new SellersComponent();

        //GIVEN
        String sellerLine = "001ç1234567891234çPedroç50000";
        Seller expected = SellerFixture.generateSeller();

        //WHEN
        Seller generated = sellersComponent.processSellerLine(sellerLine);

        //THEN
        Assertions.assertEquals(generated.toString(), expected.toString());
    }

    @Test
    public void shouldThrowErrorForNullLine() {
        //GIVEN
        SellersComponent sellersComponent = new SellersComponent();

        String nullLine = null;

        //WHEN-THEN
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            sellersComponent.processSellerLine(nullLine);
        }, "Linha não deve ser null.");
    }

    @Test
    public void shouldReturnNullInCorrectLineObject() {
        //GIVEN
        SellersComponent sellersComponent = new SellersComponent();

        String sellerLine = "0011234567891234Pedroç50000";

        //WHEN-THEN
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            sellersComponent.processSellerLine(sellerLine);
        }, "Linha deveria conter 3 parâmetros válidos.");
    }
}
