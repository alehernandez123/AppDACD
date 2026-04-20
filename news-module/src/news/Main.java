package news;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        NewsDatabaseInitializer initializer = new NewsDatabaseInitializer();
        initializer.createTable();

        NewsApiClient client = new NewsApiClient();
        String json = client.fetchNews();

        NewsParser parser = new NewsParser();
        List<NewsArticle> articles = parser.parse(json);

        NewsRepository repository = new NewsRepository();
        repository.save(articles);
    }
}