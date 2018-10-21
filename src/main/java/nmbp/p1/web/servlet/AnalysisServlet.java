package nmbp.p1.web.servlet;

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
import java.util.stream.Collectors;

@WebServlet("/servleti/analysis")
public class AnalysisServlet extends HttpServlet {
    private static final Pattern TIME_GRANULARITY_REGEX = Pattern.compile("[hd]");
    private static final DateFormat DATE_FORMAT_FORM = new SimpleDateFormat("yyyy-MM-d");
    private static final DateFormat DATE_FORMAT_DB = new SimpleDateFormat("ddMMyyyy");

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: ostatak
        String time = request.getParameter("time");
        if (time == null || time.isEmpty() || !time.matches(TIME_GRANULARITY_REGEX.pattern())) {
            response.sendError(400);
            return;
        }

        String dateStartString = request.getParameter("date-start");
        Date dateStart = null;
        if (dateStartString == null || dateStartString.isEmpty() || !dateStartString.matches("\\d{4}-\\d{2}-\\d{2}")) {
            response.sendError(400);
            return;
        }

        String dateEndString = request.getParameter("date-end");
        Date dateEnd = null;
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

        List<String> ds = days.stream().map(DATE_FORMAT_DB::format).collect(Collectors.toList());


        request.getRequestDispatcher("/WEB-INF/pages/analysis-result.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/analysis-query.jsp").forward(request, response);
    }
}
