package nmbp.p1.dao;


import nmbp.p1.model.Movie;

/**
 * Sučelje prema podsustavu za perzistenciju podataka.
 *
 * @author marcupic
 */
public interface DAO {

    void postMovie(Movie movie) throws DAOException;
}