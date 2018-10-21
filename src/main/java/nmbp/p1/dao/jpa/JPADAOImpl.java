package nmbp.p1.dao.jpa;

import nmbp.p1.dao.DAO;
import nmbp.p1.dao.DAOException;
import nmbp.p1.model.Dnevnik;
import nmbp.p1.model.Movie;
import nmbp.p1.model.SearchResult;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JPADAOImpl implements DAO {
    @Override
    public void postMovie(Movie movie) throws DAOException {
        try {
            EntityManager em = JPAEMProvider.getEntityManager();
            em.createNamedQuery("movie.insert")
                    .setParameter("id", (Integer) em.createNamedQuery("movie.get.maxid").getSingleResult() + 1)
                    .setParameter("t", movie.getTitle())
                    .setParameter("c", movie.getCategories())
                    .setParameter("s", movie.getSummary())
                    .setParameter("d", movie.getDescription())
                    .executeUpdate();
        } catch (Exception e) {
            throw new DAOException("Unable to push movie: " + movie.getTitle(), e);
        }
    }

    @Override
    public List<SearchResult> getSearchResults(String query) throws DAOException {
//        List<SearchResult> r = new LinkedList<>();
//        Query q = JPAEMProvider.getEntityManager().createNamedQuery("search.movies")
//                .setParameter("q", query);
//        List l = q.getResultList();
//        l.forEach(l -> r.add());
//
//        return

//        List<SearchResult> l = JPAEMProvider.getEntityManager().createNamedQuery("search.movies", SearchResult.class)
//                .setParameter("q", query)
//                .getResultStream().collect(Collectors.toList());

        EntityManager entityManager = JPAEMProvider.getEntityManager();
        //noinspection unchecked
        List<SearchResult> list = entityManager.createNativeQuery(TFS_QUERY, "search.result")
                .setParameter("q", query).getResultList();
        entityManager.persist(new Dnevnik(query, new Timestamp(System.currentTimeMillis())));
        return list.stream()
                .filter(s -> s.getSimilarity() > 1e-9).collect(Collectors.toList());
    }

    @Override
    public List<String> getSuggestions(String term) throws DAOException {
        return JPAEMProvider.getEntityManager()
                .createNamedQuery("autocomplete", String.class)
                .setParameter("t", term)
                .setMaxResults(5)
                .getResultList();
    }

    @Override
    public List<Map<String, Object>> getAnalysisResults(String someParameter) throws DAOException {
        //TODO: kasnije
        EntityManager em = JPAEMProvider.getEntityManager();

        em.createNamedQuery("create.temp").executeUpdate();
        //trebam funkciju koja ce vratiti Listu Stringova za datume
        List<String> l = Arrays.asList("20.10.2018", "21.10.2018", "22.10.2018");
        for (String s : l) {
            em.createNamedQuery("insert.date")
                    .setParameter("s", s)
                    .executeUpdate();
        }

//        List rs = em.createQuery(DATE_PIVOT_QUERY)
//                .setParameter("x", someParameter).getResultList();
//        em.createQuery("").getResultList();

//        List<Object[]> rs = ((Session)em.getDelegate())
//                .createSQLQuery("")
//                .setParameter("x", "d20102018 int, d21102018 int, d22102018 int, d13122018 int").getResultList();
        List x = em.createNativeQuery(DATE_PIVOT_QUERY)
//                .setParameter("x", "d20102018 int, d21102018 int, d22102018 int, d13122018 int")
                .getResultList();
//                .forEach(record -> {
//            String x = "";
//            int gejo = 5;
//        });

        int c = 1;

        return null;
    }

}
