package news;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class NewsDatabaseInitializer {

    public void createTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS news (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    title TEXT NOT NULL,
                    source TEXT,
                    published_at TEXT,
                    description TEXT,
                    url TEXT,
                    captured_at TEXT NOT NULL
                );
                """;

        try (Connection conn = DatabaseManager.connect();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            System.out.println("Tabla news creada correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
