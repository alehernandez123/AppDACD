package business;

public class Main {
    public static void main(String[] args) {
        BusinessDatamart datamart = new BusinessDatamart();

        HistoricalEventLoader loader = new HistoricalEventLoader(datamart);
        loader.load();

        BusinessEventConsumer consumer = new BusinessEventConsumer(datamart);
        consumer.start();

        BusinessCLI cli = new BusinessCLI(datamart);
        cli.start();
    }
}