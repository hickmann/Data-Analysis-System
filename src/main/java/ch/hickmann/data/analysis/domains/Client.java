package ch.hickmann.data.analysis.domains;

public class Client {

    private String name;
    private String cnpj;
    private String businessArea;

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", businessArea='" + businessArea + '\'' +
                '}';
    }

    public static Builder builder() { return new Builder(); }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getBusinessArea() {
        return businessArea;
    }

    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }

    public static final class Builder {
        private String name;
        private String cnpj;
        private String businessArea;

        private Builder() {
        }

        public static Builder aClient() {
            return new Builder();
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder cnpj(String cnpj) {
            this.cnpj = cnpj;
            return this;
        }

        public Builder businessArea(String businessArea) {
            this.businessArea = businessArea;
            return this;
        }

        public Client build() {
            Client client = new Client();
            client.setName(name);
            client.setCnpj(cnpj);
            client.setBusinessArea(businessArea);
            return client;
        }
    }
}
