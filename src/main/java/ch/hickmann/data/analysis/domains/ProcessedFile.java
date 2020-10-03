package ch.hickmann.data.analysis.domains;

import java.util.List;

public class ProcessedFile {
    private List<Seller> sellers;
    private List<Client> clients;
    private List<Sale> sales;

    public static Builder builder() { return new Builder(); }

    public List<Seller> getSellers() {
        return sellers;
    }

    public void setSellers(List<Seller> sellers) {
        this.sellers = sellers;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    public static final class Builder {
        private List<Seller> sellers;
        private List<Client> clients;
        private List<Sale> sales;

        private Builder() {
        }

        public static Builder aProcessedFile() {
            return new Builder();
        }

        public Builder sellers(List<Seller> sellers) {
            this.sellers = sellers;
            return this;
        }

        public Builder clients(List<Client> clients) {
            this.clients = clients;
            return this;
        }

        public Builder sales(List<Sale> sales) {
            this.sales = sales;
            return this;
        }

        public ProcessedFile build() {
            ProcessedFile processedFile = new ProcessedFile();
            processedFile.setSellers(sellers);
            processedFile.setClients(clients);
            processedFile.setSales(sales);
            return processedFile;
        }
    }
}
