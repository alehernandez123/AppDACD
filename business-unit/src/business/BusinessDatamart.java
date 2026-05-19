package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BusinessDatamart {

    private final List<String> newsEvents = new ArrayList<>();
    private final List<String> stockEvents = new ArrayList<>();

    private final Map<String, String> latestStockBySymbol = new HashMap<>();
    private final Map<String, Integer> newsCountBySource = new HashMap<>();

    public void addNewsEvent(String jsonEvent) {
        newsEvents.add(jsonEvent);
    }

    public void addStockEvent(String jsonEvent) {
        stockEvents.add(jsonEvent);
    }

    public List<String> getNewsEvents() {
        return newsEvents;
    }

    public List<String> getStockEvents() {
        return stockEvents;
    }

    public Map<String, String> getLatestStockBySymbol() {
        return latestStockBySymbol;
    }

    public Map<String, Integer> getNewsCountBySource() {
        return newsCountBySource;
    }
}