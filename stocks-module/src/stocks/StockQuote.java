package stocks;

public class StockQuote {
    private String symbol;
    private double price;
    private long volume;
    private double change;
    private String changePercent;
    private String capturedAt;

    public StockQuote(String symbol, double price, long volume, double change, String changePercent, String capturedAt) {
        this.symbol = symbol;
        this.price = price;
        this.volume = volume;
        this.change = change;
        this.changePercent = changePercent;
        this.capturedAt = capturedAt;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    public long getVolume() {
        return volume;
    }

    public double getChange() {
        return change;
    }

    public String getChangePercent() {
        return changePercent;
    }

    public String getCapturedAt() {
        return capturedAt;
    }
}

