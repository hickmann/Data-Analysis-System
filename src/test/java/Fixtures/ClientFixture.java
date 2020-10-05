package Fixtures;

import ch.hickmann.data.analysis.domains.Client;

public class ClientFixture {

    public static Client generateClient(){
        return Client.builder()
                .businessArea("Rural")
                .name("Jose da Silva")
                .cnpj("2345675434544345")
                .build();
    }


}
