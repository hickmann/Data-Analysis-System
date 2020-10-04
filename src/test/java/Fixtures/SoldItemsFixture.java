package Fixtures;

import ch.hickmann.data.analysis.domains.SoldItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SoldItemsFixture {

    private static final Integer RAND_MIN = 1;
    private static final Integer RAND_MAX = 1000;

    public static List<SoldItem> generateSoldItemsList(Integer size) {
        List<SoldItem> list = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            list.add(generateSoldItem());
        }

        return list;
    }

    private static Integer randomInteger() {
        Random random = new Random();
        return random.nextInt((RAND_MAX - RAND_MIN) + 1) + RAND_MIN;
    }

    public static SoldItem generateSoldItem() {

        return SoldItem.builder()
                .quantity(2)
                .price(new BigDecimal(20))
                .id(Long.valueOf(randomInteger()))
                .build();
    }

}
