package fixtures;

import ch.hickmann.data.analysis.domains.Sale;
import ch.hickmann.data.analysis.domains.SoldItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SaleFixture {

    public static Sale generateSale(){
        List<SoldItem> listSoldItems = new ArrayList<>();

        listSoldItems.add(SoldItem.builder()
                .id(Long.valueOf(2))
                .price(new BigDecimal("2.50"))
                .quantity(30)
                .build());

        listSoldItems.add(SoldItem.builder()
                .id(Long.valueOf(3))
                .price(new BigDecimal("3.10"))
                .quantity(40)
                .build());

        return Sale.builder()
                .items(listSoldItems)
                .sellerName("Pedro")
                .id(Long.valueOf("10"))
                .build();
    }


}
