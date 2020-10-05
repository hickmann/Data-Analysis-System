package components;

import fixtures.ClientFixture;
import ch.hickmann.data.analysis.components.ClientsComponent;
import ch.hickmann.data.analysis.domains.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClientsComponentTest {

    @Test
    public void shouldCreateCorrectObject() {

        ClientsComponent clientsComponent = new ClientsComponent();

        //GIVEN
        String clientLine = "002ç2345675434544345çJose da SilvaçRural";

        Client expected = ClientFixture.generateClient();

        //WHEN
        Client generated = clientsComponent.processClientLine(clientLine);

        //THEN
        Assertions.assertEquals(generated.toString(), expected.toString());
    }
}
