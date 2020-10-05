package fixtures;

import ch.hickmann.data.analysis.domains.Seller;

import java.math.BigDecimal;

public class SellerFixture {

    public static Seller generateSeller(){
        return Seller.builder()
                .wage(new BigDecimal("50000"))
                .cpf("1234567891234")
                .name("Pedro")
                .build();
    }


}
