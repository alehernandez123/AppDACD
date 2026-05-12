package stocks;

import com.google.gson.Gson;

public class Main {

    public static void main(String[] args) {

        StockApiClient client = new StockApiClient();

        String json = client.fetchQuote("IBM");

        StockParser parser = new StockParser();

        StockQuote quote = parser.parse(json);

        Event event = new Event("stocks-module", quote);

        Gson gson = new Gson();

        String jsonEvent = gson.toJson(event);

        System.out.println(jsonEvent);

        StockEventPublisher publisher = new StockEventPublisher();

        publisher.publish(jsonEvent);
    }
}