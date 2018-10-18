package nmbp.p1.web.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@WebServlet("/servleti/Auto")
public class AutoCompleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String[]> params = request.getParameterMap();

        for (int i = 0; i < 5; i++) {

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }
}
