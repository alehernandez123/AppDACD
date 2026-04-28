package stocks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StockRepository {

    public void save(StockQuote stockQuote) {
        String sql = """
                INSERT INTO stock_quotes 
                (symbol, price, volume, change, change_percent, captured_at)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = StockDatabaseManager.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, stockQuote.getSymbol());
            stmt.setDouble(2, stockQuote.getPrice());
            stmt.setLong(3, stockQuote.getVolume());
            stmt.setDouble(4, stockQuote.getChange());
            stmt.setString(5, stockQuote.getChangePercent());
            stmt.setString(6, stockQuote.getCapturedAt());

            stmt.executeUpdate();

            System.out.println("Cotización guardada correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}