package stocks;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class StockApiClient {

    private static final String API_KEY = "GPJQJ4YYPECGLJHM";

    public String fetchQuote(String symbol) {
        StringBuilder response = new StringBuilder();

        try {
            String urlString = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol="
                    + symbol + "&apikey=" + API_KEY;

            URL url = new URL(urlString);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream())
            );

            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response.toString();
    }
}
