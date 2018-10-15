package nmbp.p1.dao;


import nmbp.p1.dao.jpa.JPADAOImpl;

public class DAOProvider {

    private static DAO dao = new JPADAOImpl();

    public static DAO getDAO() {
        return dao;
    }

}