package news;

import com.google.gson.Gson;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        NewsApiClient client = new NewsApiClient();

        String json = client.fetchNews();

        NewsParser parser = new NewsParser();

        List<NewsArticle> articles = parser.parse(json);

        Gson gson = new Gson();

        NewsEventPublisher publisher =
                new NewsEventPublisher();

        for (NewsArticle article : articles) {

            Event event =
                    new Event("news-module", article);

            String jsonEvent =
                    gson.toJson(event);

            publisher.publish(jsonEvent);

            System.out.println(jsonEvent);
        }
    }
}