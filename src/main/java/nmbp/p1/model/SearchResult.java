package nmbp.p1.model;


/**
 * Rezultat naredbe rangiranja dokumenata.
 */
public class SearchResult {
    private String title;
    private String headline;
    private float similarity;

    public SearchResult(String title, String headline, float similarity) {
        this.title = title;
        this.headline = headline;
        this.similarity = similarity;
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

    public float getSimilarity() {
        return similarity;
    }

    public void setSimilarity(float similarity) {
        this.similarity = similarity;
    }
}
