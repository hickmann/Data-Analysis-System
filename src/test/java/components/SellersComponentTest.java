package components;

import Fixtures.SellerFixture;
import ch.hickmann.data.analysis.components.SellersComponent;
import ch.hickmann.data.analysis.domains.Client;
import ch.hickmann.data.analysis.domains.Seller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

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
}
