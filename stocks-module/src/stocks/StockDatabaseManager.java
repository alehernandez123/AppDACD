package stocks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class StockDatabaseManager {

    private static final String URL = "jdbc:sqlite:stocks.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
