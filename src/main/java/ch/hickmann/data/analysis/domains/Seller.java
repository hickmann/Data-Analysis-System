package ch.hickmann.data.analysis.domains;

import java.math.BigDecimal;

public class Seller {

    private String name;

    private String cpf;
    private BigDecimal wage;

    @Override
    public String toString() {
        return "Seller{" +
                "name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                ", wage=" + wage +
                '}';
    }

    public Seller() {
    }

    public static Builder builder() { return new Builder(); }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public BigDecimal getWage() {
        return wage;
    }

    public void setWage(BigDecimal wage) {
        this.wage = wage;
    }

    public static final class Builder {
        private String name;
        private String cpf;
        private BigDecimal wage;

        private Builder() {
        }

        public static Builder aSeller() {
            return new Builder();
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder cpf(String cpf) {
            this.cpf = cpf;
            return this;
        }

        public Builder wage(BigDecimal wage) {
            this.wage = wage;
            return this;
        }

        public Seller build() {
            Seller seller = new Seller();
            seller.setName(name);
            seller.setCpf(cpf);
            seller.setWage(wage);
            return seller;
        }
    }
}
