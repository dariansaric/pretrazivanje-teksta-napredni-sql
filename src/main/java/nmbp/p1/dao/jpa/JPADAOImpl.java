package nmbp.p1.dao.jpa;

import nmbp.p1.dao.DAO;
import nmbp.p1.dao.DAOException;
import nmbp.p1.model.Movie;

import javax.persistence.EntityManager;

public class JPADAOImpl implements DAO {
    //TODO: sql za izvršenje pretraživanja
    @Override
    public void postMovie(Movie movie) throws DAOException {
        try {
            EntityManager em = JPAEMProvider.getEntityManager();
//            movie.setMovieid((Integer) em.createNamedQuery("movie.get.maxid").getSingleResult() + 1);
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
}
