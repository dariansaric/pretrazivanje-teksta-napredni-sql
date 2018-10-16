package nmbp.p1.dao;


import nmbp.p1.model.Movie;
import nmbp.p1.model.SearchResult;

import java.util.List;

/**
 * Sučelje prema podsustavu za perzistenciju podataka.
 *
 * @author marcupic
 */
public interface DAO {
    String TFS_QUERY =
            "select title," +
                    "   ts_headline('english', title || '\n<p>' || summary || '</p>\n' || categories || '\n' || description, " +
                    "       to_tsquery(:q)) as headline" +
                    " from " +
                    "     (select title, " +
                    "       summary, categories, description, " +
                    "       ts_rank(array[0.2,0.3,0.6,1.0], searchvector," +
                    "       to_tsquery(:q), 2) as r " +
                    "       from movie order by r desc limit 10) as ranks;";

    void postMovie(Movie movie) throws DAOException;

    List<SearchResult> getSearchResults(String query) throws DAOException;

    //TODO:Napisi metodu za dohvat rezultata (objekt za reprezentaciju, reprezentacija)
}