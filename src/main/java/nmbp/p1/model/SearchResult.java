package nmbp.p1.model;


/**
 * Rezultat naredbe rangiranja dokumenata.
 */
public class SearchResult {
    private String title;
    private String headline;

    public SearchResult(String title, String headline) {
        this.title = title;
        this.headline = headline;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
