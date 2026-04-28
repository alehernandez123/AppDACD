package news;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NewsScheduler {

    public void start() {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        Runnable task = () -> {
            System.out.println("Capturando noticias...");

            NewsDatabaseInitializer initializer = new NewsDatabaseInitializer();
            initializer.createTable();

            NewsApiClient client = new NewsApiClient();
            String json = client.fetchNews();

            NewsParser parser = new NewsParser();
            List<NewsArticle> articles = parser.parse(json);

            NewsRepository repository = new NewsRepository();
            repository.save(articles);

            System.out.println("Captura de noticias finalizada.");
        };

        scheduler.scheduleAtFixedRate(task, 0, 1, TimeUnit.HOURS);
    }
}
