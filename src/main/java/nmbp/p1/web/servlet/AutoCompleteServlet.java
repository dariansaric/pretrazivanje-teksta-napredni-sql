package nmbp.p1.web.servlet;

import nmbp.p1.dao.DAOProvider;
import org.json.JSONArray;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static nmbp.p1.web.Util.parseForm;
import static nmbp.p1.web.Util.prepareTSQuery;

@WebServlet("/servleti/Auto")
public class AutoCompleteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        String term = request.getParameter("term");
        List<String> terms = parseForm(term);
        String query = prepareTSQuery(terms.subList(terms.size() - 1, terms.size()), "and");

        if (!query.isEmpty()) {
            List<String> t = DAOProvider.getDAO().getSuggestions(query);
            response.getWriter().print(new JSONArray(t));
        }
    }
}
