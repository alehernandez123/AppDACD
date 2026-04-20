package news;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class NewsRepository {

    public void save(List<NewsArticle> articles) {
        String sql = """
                INSERT INTO news (title, source, published_at, description, url, captured_at)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (NewsArticle article : articles) {
                stmt.setString(1, article.getTitle());
                stmt.setString(2, article.getSource());
                stmt.setString(3, article.getPublishedAt());
                stmt.setString(4, article.getDescription());
                stmt.setString(5, article.getUrl());
                stmt.setString(6, article.getCapturedAt());

                stmt.executeUpdate();
            }

            System.out.println("Noticias guardadas correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
