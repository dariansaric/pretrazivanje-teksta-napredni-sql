package nmbp.p1.dao.jpa;

import nmbp.p1.dao.DAO;
import nmbp.p1.dao.DAOException;
import nmbp.p1.model.Dnevnik;
import nmbp.p1.model.Movie;
import nmbp.p1.model.SearchResult;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.List;
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

}
