package stocks;

public class Main {
    public static void main(String[] args) {

        // 1. Crear tabla
        StockDatabaseInitializer initializer = new StockDatabaseInitializer();
        initializer.createTable();

        // 2. Obtener datos API
        StockApiClient client = new StockApiClient();
        String json = client.fetchQuote("IBM");

        // 3. Parsear JSON
        StockParser parser = new StockParser();
        StockQuote quote = parser.parse(json);

        // 4. Guardar en BD
        StockRepository repository = new StockRepository();
        repository.save(quote);
    }
}
