package nmbp.p1.web.form;

import nmbp.p1.model.Movie;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static nmbp.p1.web.Util.serveProperty;

public class AddForm {
    private String title;
    private String categories;
    private String summary;
    private String description;
    private Map<String, String> errors = new HashMap<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Adds an error message entry.
     *
     * @param key   error key
     * @param value error message
     */
    void putError(String key, String value) {
        errors.put(key, value);
    }

    /**
     * Clears {@linkplain #errors} map.
     */
    void clearErrorMap() {
        errors.clear();
    }

    /**
     * Returns the message for the provided property error.
     *
     * @param errorName property with the error
     *
     * @return error message mapped to the provided property
     */
    public String getError(String errorName) {
        return errors.get(errorName);
    }

    /**
     * Returns true if a form contains any errors.
     *
     * @return true if <tt>!errors.isEmpty()</tt>
     */
    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    /**
     * Returns true if {@linkplain #errors} contains an error for the provided property.
     *
     * @param errorName property name
     *
     * @return true if <tt>errors.containsKey(errorName)</tt>
     */
    public boolean hasError(String errorName) {
        return errors.containsKey(errorName);
    }

    private void validate() {
        clearErrorMap();

        if (title.isEmpty()) {
            putError("title", "You must type in a title!!");
        }

        if (categories.isEmpty()) {
            putError("categories", "You must type in at least one category!!");
        } else if (!categories.matches(Movie.CATEGORY_PATTERN.pattern())) {
            putError("categories", "Invalid category listing, try this: <category>;<other-category>...!!");
        }

        if (summary.isEmpty()) {
            putError("summary", "You must type in a summary!!");
        }

        if (description.isEmpty()) {
            putError("description", "You must type in a description!!");
        }
    }

    public void fillFromHttp(HttpServletRequest req) {
        title = serveProperty(req.getParameter("title"));
        summary = serveProperty(req.getParameter("summary"));
        categories = serveProperty(req.getParameter("categories"));
        description = serveProperty(req.getParameter("description"));

        validate();
    }

    public Movie fillMovie() {
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setSummary(summary);
        movie.setDescription(description);
        movie.setCategories(categories);

        return movie;
    }
}
