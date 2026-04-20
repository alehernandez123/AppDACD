package news;

public class NewsArticle {
    private String title;
    private String source;
    private String publishedAt;
    private String description;
    private String url;
    private String capturedAt;

    public NewsArticle(String title, String source, String publishedAt, String description, String url, String capturedAt) {
        this.title = title;
        this.source = source;
        this.publishedAt = publishedAt;
        this.description = description;
        this.url = url;
        this.capturedAt = capturedAt;
    }

    public String getTitle() {
        return title;
    }

    public String getSource() {
        return source;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getCapturedAt() {
        return capturedAt;
    }
}
