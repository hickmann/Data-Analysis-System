package ch.hickmann.data.analysis.domains;

import java.math.BigDecimal;

public class SoldItem {
    private Long id;
    private Integer quantity;
    private BigDecimal price;

    public BigDecimal priceSum() {
        return getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    public static Builder builder() { return new Builder(); }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public static final class Builder {
        private Long id;
        private Integer quantity;
        private BigDecimal price;

        private Builder() {
        }

        public static Builder aSoldItem() {
            return new Builder();
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder quantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public SoldItem build() {
            SoldItem soldItem = new SoldItem();
            soldItem.setId(id);
            soldItem.setQuantity(quantity);
            soldItem.setPrice(price);
            return soldItem;
        }
    }
}
