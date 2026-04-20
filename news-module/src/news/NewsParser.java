package news;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NewsParser {

    public List<NewsArticle> parse(String json) {
        List<NewsArticle> articlesList = new ArrayList<>();

        JsonObject root = JsonParser.parseString(json).getAsJsonObject();
        JsonArray articles = root.getAsJsonArray("articles");

        for (int i = 0; i < articles.size(); i++) {
            JsonObject article = articles.get(i).getAsJsonObject();

            String title = getSafeString(article, "title");
            String description = getSafeString(article, "description");
            String url = getSafeString(article, "url");
            String publishedAt = getSafeString(article, "publishedAt");
            String capturedAt = LocalDateTime.now().toString();

            String source = "";
            if (article.has("source") && article.get("source").isJsonObject()) {
                JsonObject sourceObject = article.getAsJsonObject("source");
                source = getSafeString(sourceObject, "name");
            }

            NewsArticle newsArticle = new NewsArticle(
                    title,
                    source,
                    publishedAt,
                    description,
                    url,
                    capturedAt
            );

            articlesList.add(newsArticle);
        }

        return articlesList;
    }

    private String getSafeString(JsonObject object, String key) {
        if (object.has(key) && !object.get(key).isJsonNull()) {
            return object.get(key).getAsString();
        }
        return "";
    }

}
