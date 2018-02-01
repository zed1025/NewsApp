package inc.thebest.newsapp;

/**
 * Created by amitkumar on 24/01/18 at 17:01.
 */

public class News {
    private String news_title;
    private String webURl;

    public News(String news_title) {
        this.news_title = news_title;
    }

    public News(String news_title, String webURl) {
        this.news_title = news_title;
        this.webURl = webURl;
    }

    public String getNews_title() {
        return news_title;
    }

    public void setNews_title(String news_title) {
        this.news_title = news_title;
    }

    public String getWebURl() {
        return webURl;
    }

    public void setWebURl(String webURl) {
        this.webURl = webURl;
    }
}
