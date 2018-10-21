package nmbp.p1.web.servlet;

import nmbp.p1.dao.DAOProvider;
import nmbp.p1.web.form.AddForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servleti/add")
public class AddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AddForm form = new AddForm();

        req.setAttribute("form", form);

        req.getRequestDispatcher("/WEB-INF/pages/add-movie.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        AddForm form = new AddForm();
        form.fillFromHttp(req);
        if (form.hasErrors()) {
            req.setAttribute("form", form);
            req.getRequestDispatcher("/WEB-INF/pages/add-movie.jsp").forward(req, resp);
            return;
        }

        DAOProvider.getDAO().postMovie(form.fillMovie());
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}
