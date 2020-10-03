package ch.hickmann.data.analysis.domains;

import java.math.BigDecimal;
import java.util.List;

public class Sale {

    private Long id;
    private List<SoldItem> items;
    private String sellerName;

    public BigDecimal totalValue() {
        return items.stream()
                .map(SoldItem::priceSum)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    public static Builder builder() { return new Builder(); }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<SoldItem> getItems() {
        return items;
    }

    public void setItems(List<SoldItem> items) {
        this.items = items;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public static final class Builder {
        private Long id;
        private List<SoldItem> items;
        private String sellerName;

        private Builder() {
        }

        public static Builder aSale() {
            return new Builder();
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder items(List<SoldItem> items) {
            this.items = items;
            return this;
        }

        public Builder sellerName(String sellerName) {
            this.sellerName = sellerName;
            return this;
        }

        public Sale build() {
            Sale sale = new Sale();
            sale.setId(id);
            sale.setItems(items);
            sale.setSellerName(sellerName);
            return sale;
        }
    }
}
