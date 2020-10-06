package components;

import ch.hickmann.data.analysis.components.ClientsComponent;
import ch.hickmann.data.analysis.components.SoldItemsComponent;
import ch.hickmann.data.analysis.domains.Client;
import ch.hickmann.data.analysis.domains.SoldItem;
import fixtures.ClientFixture;
import fixtures.SoldItemsFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SolItemsComponentTest {

    @Test
    public void shouldCreateCorrectObject() {

        SoldItemsComponent soldItemsComponent = new SoldItemsComponent();

        //GIVEN
        String soldItemsLine = "1-2-20,1-2-20";

        List<SoldItem> expected = SoldItemsFixture.generateSoldItemsList(2);

        //WHEN
        List<SoldItem> generated = soldItemsComponent.extractSoldItems(soldItemsLine);

        //THEN
        Assertions.assertEquals(generated.toString(), expected.toString());
    }

}
