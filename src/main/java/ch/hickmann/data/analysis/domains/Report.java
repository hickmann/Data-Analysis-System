package ch.hickmann.data.analysis.domains;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

import static java.util.Objects.isNull;

public class Report {
    private String path;
    private Long mostExpensiveSaleId;
    private Seller worstSeller;
    private Integer clientsQuantity;
    private Integer sellersQuantity;

    public Report(ProcessedFile processedFile, String path) {
        clientsQuantity = processedFile.getClients().size();
        sellersQuantity = processedFile.getSellers().size();
        worstSeller = extractWorstSeller(processedFile);
        mostExpensiveSaleId = extractMostExpensiveSaleId(processedFile);
        this.path = path;
    }

    public Report() {
    }

    private Long extractMostExpensiveSaleId(ProcessedFile processedFile) {
        return processedFile.getSales()
                .stream()
                .max(Comparator.comparing(Sale::totalValue))
                .map(Sale::getId)
                .orElse(Long.valueOf(0));
    }

    private BigDecimal getTotalSalesByName(List<Sale> saleList, String name) {
        return saleList.stream()
                .filter(sale -> sale.getSellerName().equalsIgnoreCase(name))
                .map(Sale::totalValue)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    private Seller extractWorstSeller(ProcessedFile processedFile) {
        BigDecimal totalSoldAux = null;
        Seller worstSeller = null;

        for (Seller seller: processedFile.getSellers()) {
            BigDecimal totalSoldBySeller = getTotalSalesByName(processedFile.getSales(), seller.getName());

            if (isNull(totalSoldAux) || totalSoldBySeller.compareTo(totalSoldAux) < 0) {
                totalSoldAux = totalSoldBySeller;
                worstSeller = seller;
            }
        }

        return worstSeller;
    }

    @Override
    public String toString() {
        return "Report{" +
                "path='" + path + '\'' +
                ", mostExpensiveSaleId=" + mostExpensiveSaleId +
                ", worstSeller=" + worstSeller +
                ", clientsQuantity=" + clientsQuantity +
                ", sellersQuantity=" + sellersQuantity +
                '}';
    }

    public String toStringFormatted() {
        return "Relatório para o arquivo " + getPath() + System.getProperty("line.separator") +
                "Número de Clientes: " + getClientsQuantity() + System.getProperty("line.separator") +
                "Número de Vendedores: " + getSellersQuantity() + System.getProperty("line.separator") +
                "Venda mais cara: " + getMostExpensiveSaleId() + System.getProperty("line.separator") +
                "Pior desempenho: " + System.getProperty("line.separator") + getWorstSeller() + System.getProperty("line.separator");

    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getMostExpensiveSaleId() {
        return mostExpensiveSaleId;
    }

    public void setMostExpensiveSaleId(Long mostExpensiveSaleId) {
        this.mostExpensiveSaleId = mostExpensiveSaleId;
    }

    public Seller getWorstSeller() {
        return worstSeller;
    }

    public void setWorstSeller(Seller worstSeller) {
        this.worstSeller = worstSeller;
    }

    public Integer getClientsQuantity() {
        return clientsQuantity;
    }

    public void setClientsQuantity(Integer clientsQuantity) {
        this.clientsQuantity = clientsQuantity;
    }

    public Integer getSellersQuantity() {
        return sellersQuantity;
    }

    public void setSellersQuantity(Integer sellersQuantity) {
        this.sellersQuantity = sellersQuantity;
    }

    public static final class Builder {
        private String path;
        private Long mostExpensiveSaleId;
        private Seller worstSeller;
        private Integer clientsQuantity;
        private Integer sellersQuantity;

        private Builder() {
        }

        public static Builder aReport() {
            return new Builder();
        }

        public Builder path(String path) {
            this.path = path;
            return this;
        }

        public Builder mostExpensiveSaleId(Long mostExpensiveSaleId) {
            this.mostExpensiveSaleId = mostExpensiveSaleId;
            return this;
        }

        public Builder worstSeller(Seller worstSeller) {
            this.worstSeller = worstSeller;
            return this;
        }

        public Builder clientsQuantity(Integer clientsQuantity) {
            this.clientsQuantity = clientsQuantity;
            return this;
        }

        public Builder sellersQuantity(Integer sellersQuantity) {
            this.sellersQuantity = sellersQuantity;
            return this;
        }

        public Report build() {
            Report report = new Report();
            report.setPath(path);
            report.setMostExpensiveSaleId(mostExpensiveSaleId);
            report.setWorstSeller(worstSeller);
            report.setClientsQuantity(clientsQuantity);
            report.setSellersQuantity(sellersQuantity);
            return report;
        }
    }
}
