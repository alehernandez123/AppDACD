package stocks;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class StockDatabaseInitializer {

    public void createTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS stock_quotes (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    symbol TEXT NOT NULL,
                    price REAL NOT NULL,
                    volume INTEGER,
                    change REAL,
                    change_percent TEXT,
                    captured_at TEXT NOT NULL
                );
                """;

        try (Connection conn = StockDatabaseManager.connect();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            System.out.println("Tabla stock_quotes creada correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
