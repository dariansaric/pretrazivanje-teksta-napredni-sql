package nmbp.p1.web.init;


import nmbp.p1.dao.jpa.JPAEMFProvider;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * This {@linkplain WebListener} generates an {@linkplain EntityManagerFactory} on startup and stores it as a
 * servlet context attribute.
 */
@WebListener
public class Inicijalizacija implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //TODO:otkrij zasto ne radi
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("nmbp.projekt1");
        sce.getServletContext().setAttribute("emf", emf);
        JPAEMFProvider.setEmf(emf);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        JPAEMFProvider.setEmf(null);
        EntityManagerFactory emf = (EntityManagerFactory) sce.getServletContext().getAttribute("emf");
        if (emf != null) {
            emf.close();
        }
    }
}