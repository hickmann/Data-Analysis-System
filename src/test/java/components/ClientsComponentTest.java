package components;

import ch.hickmann.data.analysis.components.ClientsComponent;
import ch.hickmann.data.analysis.domains.Client;
import fixtures.ClientFixture;
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

    @Test
    public void shouldReturnNullInCorrectLineObject() {

        ClientsComponent clientsComponent = new ClientsComponent();

        //GIVEN
        String clientLine = "0022345675434544345Jose da SilvaRural";

        //WHEN-THEN
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            clientsComponent.processClientLine(clientLine);
        }, "Linha deveria conter 3 parâmetros válidos.");
    }

    @Test
    public void shouldThrowErrorForNullLine() {
        ClientsComponent clientsComponent = new ClientsComponent();
        //GIVEN
        String nullLine = null;

        //WHEN-THEN
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            clientsComponent.processClientLine(nullLine);
        }, "Linha não deve ser null.");
    }

}
