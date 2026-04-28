package stocks;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.time.LocalDateTime;

public class StockParser {

    public StockQuote parse(String json) {
        JsonObject root = JsonParser.parseString(json).getAsJsonObject();

        JsonObject quote = root.getAsJsonObject("Global Quote");

        String symbol = getSafeString(quote, "01. symbol");
        double price = getSafeDouble(quote, "05. price");
        long volume = getSafeLong(quote, "06. volume");
        double change = getSafeDouble(quote, "09. change");
        String changePercent = getSafeString(quote, "10. change percent");
        String capturedAt = LocalDateTime.now().toString();

        return new StockQuote(symbol, price, volume, change, changePercent, capturedAt);
    }

    private String getSafeString(JsonObject object, String key) {
        if (object.has(key) && !object.get(key).isJsonNull()) {
            return object.get(key).getAsString();
        }
        return "";
    }

    private double getSafeDouble(JsonObject object, String key) {
        if (object.has(key) && !object.get(key).isJsonNull()) {
            return Double.parseDouble(object.get(key).getAsString());
        }
        return 0.0;
    }

    private long getSafeLong(JsonObject object, String key) {
        if (object.has(key) && !object.get(key).isJsonNull()) {
            return Long.parseLong(object.get(key).getAsString());
        }
        return 0;
    }
}
