package nmbp.p1.web.servlet;

import nmbp.p1.dao.DAO;
import nmbp.p1.dao.DAOProvider;
import nmbp.p1.model.SearchResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import static nmbp.p1.web.Util.*;

@WebServlet("/servleti/search")
public class SearchServlet extends HttpServlet {
    private static final Pattern QUERY_PATTERN = Pattern.compile("((\".+\")|[\\w.]+)(\\s((\".*\")|([\\w.]+)))*");
    private static final Pattern OPERATION_PATTERN = Pattern.compile("(or)|(and)");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/search-query.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // TODO: definiraj poruke greške za upis parametara upita
        String query = req.getParameter("query");
        if (query == null || query.isEmpty() || !query.matches(QUERY_PATTERN.pattern())) {
            resp.sendError(400, "Bad request");
            return;
        }
        String operation = req.getParameter("operation");
        if (operation == null || operation.isEmpty() || !operation.matches(OPERATION_PATTERN.pattern())) {
            resp.sendError(400, "Bad request");
            return;
        }

        query = prepareTSQuery(parseForm(query), operation);
        List<SearchResult> results = DAOProvider.getDAO().getSearchResults(query);
        req.setAttribute("results", prepareResults(results));
        // TODO: Uljepšaj ispis izvršenog SQL upita
        req.setAttribute("sql", DAO.TFS_QUERY.replaceAll(":q", query).replaceAll("\n", ""));

        req.getRequestDispatcher("/WEB-INF/pages/search-result.jsp").forward(req, resp);
    }
}
