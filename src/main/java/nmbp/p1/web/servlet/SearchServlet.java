package nmbp.p1.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static nmbp.p1.web.Util.prepareTSQuery;

@WebServlet("/servleti/search")
public class SearchServlet extends HttpServlet {
    private static final Pattern QUERY_PATTERN = Pattern.compile("((\".+\")|\\w+)(\\s((\".*\")|(\\w+)))*");
    private static final Pattern OPERATION_PATTERN = Pattern.compile("(or)|(and)");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/search-query.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String query = req.getParameter("query");
        if (query == null || query.isEmpty() || !query.matches(QUERY_PATTERN.pattern())) {
            resp.sendError(400, "Bad request");
        }
        String operation = req.getParameter("operation");
        if (operation == null || operation.isEmpty() || !operation.matches(OPERATION_PATTERN.pattern())) {
            resp.sendError(400, "Bad request");
        }

        //defaultna pretraga, dok ne napravim parser za query
        List<String> querires = Arrays.asList("Dancing", "Legend of Tarzan", "Lord Of Dance");
        query = prepareTSQuery(querires, operation);


    }
}
