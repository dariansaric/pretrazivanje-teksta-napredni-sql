package nmbp.p1.model;

import org.postgresql.util.PGobject;

import javax.persistence.*;
import java.util.regex.Pattern;

@NamedQueries({
        @NamedQuery(name = "movie.get.maxid", query = "select max(movieid) from Movie"),
        @NamedQuery(name = "autocomplete",
                query = "select summary from Movie order by similarity(summary, :t) desc, length(summary), summary"
        )
})
@NamedNativeQueries({
        @NamedNativeQuery(name = "movie.insert", query = "insert into movie" +
                "(movieid, title, categories, summary, description)" +
                "values (:id, :t, :c, :s, :d);"),
        @NamedNativeQuery(name = "search.movies",
                query = "select title," +
                        "   ts_headline('english', title || '\\n' || summary || '\\n' || categories || '\\n' || description, " +
                        "       to_tsquery(:q)) as headline, rank" +
                        " from " +
                        "     (select title, " +
                        "       summary, categories, description, " +
                        "       ts_rank(array[0.2,0.3,0.6,1.0], searchvector," +
                        "       to_tsquery(:q), 2) as rank " +
                        "       from movie order by rank desc limit 10) as ranks;")
//        @NamedNativeQuery(name = "autocomplete",
//        query = "select summary " +
//                "            from movie " +
//                "            order by levenshtein_less_equal(lower(summary), lower(:t), length(summary) / 4), " +
//                "            ts_rank(to_tsvector(summary), to_tsquery(:t)) desc, " +
//                "            length(summary) asc, summary " +
//                "            limit 5;")
})

@SqlResultSetMappings(
        @SqlResultSetMapping(name = "search.result", classes = {
                @ConstructorResult(targetClass = SearchResult.class,
                        columns = {@ColumnResult(name = "title", type = String.class),
                                @ColumnResult(name = "headline", type = String.class),
                                @ColumnResult(name = "rank", type = Float.class)})
        })
)

@Entity
public class Movie {
    public static final Pattern CATEGORY_PATTERN = Pattern.compile("[\\w-]+(;[\\w-]+)*");
    private int movieid;
    private String title;
    private String categories;
    private String summary;
    private String description;
    private PGobject searchVector = null;

    @Id
    @Column(name = "movieid", nullable = false)
    public int getMovieid() {
        return movieid;
    }

    public void setMovieid(int movieid) {
        this.movieid = movieid;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 255)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "categories", nullable = false, length = 255)
    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    @Basic
    @Column(name = "summary", nullable = false, length = -1)
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Basic
    @Column(name = "description", nullable = false, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (movieid != movie.movieid) return false;
        if (title != null ? !title.equals(movie.title) : movie.title != null) return false;
        if (categories != null ? !categories.equals(movie.categories) : movie.categories != null) return false;
        if (summary != null ? !summary.equals(movie.summary) : movie.summary != null) return false;
        return description != null ? description.equals(movie.description) : movie.description == null;
    }

    @Override
    public int hashCode() {
        int result = movieid;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (categories != null ? categories.hashCode() : 0);
        result = 31 * result + (summary != null ? summary.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Column
    public PGobject getSearchVector() {
        return searchVector;
    }

    public void setSearchVector(PGobject searchVector) {
        this.searchVector = searchVector;
    }
}
