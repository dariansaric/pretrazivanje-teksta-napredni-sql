package nmbp.p1.dao;


import nmbp.p1.model.Movie;
import nmbp.p1.model.SearchResult;

import java.util.List;
import java.util.Map;

/**
 * Sučelje prema podsustavu za perzistenciju podataka.
 *
 * @author marcupic
 */
public interface DAO {
    String TFS_QUERY =
            "select title," +
                    "   ts_headline('english', title || '\n' || summary || '\n' || categories || '\n' || description, " +
                    "   to_tsquery(:q)) as headline, rank" +
                    " from " +
                    "     (select title, " +
                    "       summary, categories, description, " +
                    "       ts_rank_cd(array[0.2,0.3,0.6,1.0], searchvector," +
                    "       to_tsquery(:q), 2) as rank " +
                    "       from movie order by rank desc limit 10) as ranks;";

    //    String FUZZY_QUERY = "select summary," +
//            "from movie " +
//            "order by levenshtein_less_equal(lower(summary), lower(:t), length(summary) / 4), " +
//            "ts_rankcd(to_tsvector(summary), to_tsquery(:t)) desc, " +
//            "length(summary) asc, summary " +
//            "limit 5;";
    String DATE_FORMAT = "DD.MM.YYYY";
    String DATE_PIVOT_QUERY = "select * from " +
            "crosstab( " +
            "    'select upit, to_char(datum, ''DD.MM.YYYY.'') as d, count(*) as br from dnevnik " +
            "group by upit, d order by upit, d','select t from dani order by dan')" +
            "  as pivot(upit varchar(255), d20102018 int, d21102018 int, d22102018 int);";

    void postMovie(Movie movie) throws DAOException;

    List<SearchResult> getSearchResults(String query) throws DAOException;

    List<String> getSuggestions(String term) throws DAOException;

    List<Map<String, Object>> getAnalysisResults(String someParameter) throws DAOException;
}