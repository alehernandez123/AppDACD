package stocks;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class StockScheduler {

    public void start() {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        Runnable task = () -> {
            System.out.println("Capturando cotización...");

            StockDatabaseInitializer initializer = new StockDatabaseInitializer();
            initializer.createTable();

            StockApiClient client = new StockApiClient();
            String json = client.fetchQuote("IBM");

            StockParser parser = new StockParser();
            StockQuote quote = parser.parse(json);

            StockRepository repository = new StockRepository();
            repository.save(quote);

            System.out.println("Captura de cotización finalizada.");
        };

        scheduler.scheduleAtFixedRate(task, 0, 1, TimeUnit.HOURS);
    }
}
