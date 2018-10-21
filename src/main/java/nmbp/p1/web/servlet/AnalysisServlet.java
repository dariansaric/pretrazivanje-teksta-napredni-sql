package nmbp.p1.web.servlet;

import nmbp.p1.dao.DAO;
import nmbp.p1.dao.DAOProvider;
import nmbp.p1.web.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

import static nmbp.p1.web.Util.getDays;

@WebServlet("/servleti/analysis")
public class AnalysisServlet extends HttpServlet {
    private static final Pattern TIME_GRANULARITY_REGEX = Pattern.compile("[hd]");
    private static final DateFormat DATE_FORMAT_FORM = new SimpleDateFormat("yyyy-MM-d");

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: ostatak
        String time = request.getParameter("time");
        if (time == null || time.isEmpty() || !time.matches(TIME_GRANULARITY_REGEX.pattern())) {
            response.sendError(400);
            return;
        }

        String dateStartString = request.getParameter("date-start");
        Date dateStart;
        if (dateStartString == null || dateStartString.isEmpty() || !dateStartString.matches("\\d{4}-\\d{2}-\\d{2}")) {
            response.sendError(400);
            return;
        }

        String dateEndString = request.getParameter("date-end");
        Date dateEnd;
        if (dateEndString == null || dateEndString.isEmpty() || !dateEndString.matches("\\d{4}-\\d{2}-\\d{2}")) {
            response.sendError(400);
            return;
        }

        try {
            dateStart = DATE_FORMAT_FORM.parse(dateStartString);
            dateEnd = DATE_FORMAT_FORM.parse(dateEndString);
        } catch (ParseException e) {
            response.sendError(400, "greška pri parsiranju datuma");
            return;
        }

        if (dateStart.compareTo(dateEnd) > 0) {
            Date tmp = dateStart;
            dateStart = dateEnd;
            dateEnd = tmp;
        }


        List<Util.PivotResult> results = time.equals("d") ?
                pivotForDates(dateStart, dateEnd) :
                DAOProvider.getDAO().getAnalysisResultsForHours(dateStart, dateEnd);

        request.setAttribute("results", results);
        List<String> headers;
        if (time.equals("d")) {

            headers = getDays(dateStart, dateEnd);
            headers.add(0, "Upit");
        } else {
            headers = new ArrayList<>(25);
            headers.add("Upit");
            headers.addAll(Arrays.asList(DAO.HOURS));
        }
        request.setAttribute("headers", headers);


        request.getRequestDispatcher("/WEB-INF/pages/analysis-result.jsp").forward(request, response);
    }

    private List<Util.PivotResult> pivotForDates(Date dateStart, Date dateEnd) {
        Set<Date> days = new LinkedHashSet<>();
        days.add(dateStart);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(dateStart);

        while (calendar.getTime().before(dateEnd)) {
            Date result = calendar.getTime();
            days.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        days.add(dateEnd);

        return DAOProvider.getDAO().getAnalysisResultsForDays(
                getDays(dateStart, dateEnd));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/analysis-query.jsp").forward(request, response);
    }
}
