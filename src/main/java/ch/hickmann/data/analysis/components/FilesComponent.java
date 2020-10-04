package ch.hickmann.data.analysis.components;

import ch.hickmann.data.analysis.domains.Client;
import ch.hickmann.data.analysis.domains.ProcessedFile;
import ch.hickmann.data.analysis.domains.Sale;
import ch.hickmann.data.analysis.domains.Seller;
import ch.hickmann.data.analysis.domains.exceptions.FileException;
import ch.hickmann.data.analysis.saga.services.SagaService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class FilesComponent {

    private static final Logger logger = LoggerFactory.getLogger(FilesComponent.class);
    private static final String SELLER_CODE = "001";
    private static final String CLIENT_CODE = "002";
    private static final String SALE_CODE = "003";

    private static final Integer LAYOUT_CODE_BEGINNING = 0;
    private static final Integer LAYOUT_CODE_END = 3;

    private final SellersComponent sellersComponent;
    private final ClientsComponent clientsComponent;
    private final SalesComponent salesComponent;
    private final SagaService sagaService;

    public FilesComponent(SellersComponent sellersComponent, ClientsComponent clientsComponent, SalesComponent salesComponent, SagaService sagaService) {
        this.sellersComponent = sellersComponent;
        this.clientsComponent = clientsComponent;
        this.salesComponent = salesComponent;
        this.sagaService = sagaService;
    }

    public ProcessedFile process(String path) {
        logger.info("Starting analyze for - {}", path);

        List<String> fileLines = readFileLines(path);
        ProcessedFile processedFile = process(fileLines);

        return processedFile;
    }

    public ProcessedFile process(List<String> fileLines) {
        List<Client> clients = new ArrayList<>();
        List<Seller> sellers = new ArrayList<>();
        List<Sale> sales = new ArrayList<>();

        for (String line : new ArrayList<>(fileLines)) {
            switch (getLayoutCode(line)) {
                case SELLER_CODE:
                    sellers.add(sellersComponent.processSellerLine(line));
                    break;
                case CLIENT_CODE:
                    clients.add(clientsComponent.processClientLine(line));
                    break;
                case SALE_CODE:
                    sales.add(salesComponent.processSaleLine(line));
                    break;
            }
        }

        return ProcessedFile.builder()
                .clients(clients)
                .sales(sales)
                .sellers(sellers)
                .build();
    }

    private String getLayoutCode(String fileLines) {
        return fileLines.substring(LAYOUT_CODE_BEGINNING, LAYOUT_CODE_END);
    }

    private List<String> readFileLines(String path) {
        String file = readFile(path);
        return Arrays.asList(file.split(System.lineSeparator()));
    }

    public String readFile(String path) {
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            sagaService.error(path);
            logger.error("It was not possible to read file. - {}", path);
            throw new FileException("It was not possible to read file.");
        }
        return new String(bytes);
    }

    public void saveReport(String path, String content) throws IOException {
        FileUtils.write(new File(path), content, "UTF-8");
    }
}
