package news;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NewsApiClient {
    private static final String Api_key = "0db6e1e7d76944689ccd93083c6f3595";

    public String fetchNews() {
        StringBuilder response = new StringBuilder();

        try {
            String urlString = "https://newsapi.org/v2/top-headlines?country=us&apiKey=" + Api_key;
            URL url = new URL(urlString);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
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
