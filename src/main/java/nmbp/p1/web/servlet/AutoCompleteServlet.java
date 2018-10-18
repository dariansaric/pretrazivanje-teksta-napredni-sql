package nmbp.p1.web.servlet;

import org.json.JSONArray;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/servleti/Auto")
public class AutoCompleteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        String term = request.getParameter("term");

        response.getWriter().print(new JSONArray(Arrays.asList("bla", "ble", "bli")));
    }
}
