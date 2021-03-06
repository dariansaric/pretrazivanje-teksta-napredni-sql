package nmbp.p1.web;


import nmbp.p1.model.SearchResult;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * This class is a utility class used for byte-StringHex conversion in both directions.
 *
 * @author Darian Šarić
 */
public class Util {
    private static final DateFormat DATE_FORMAT_DB = new SimpleDateFormat("ddMMyyyy");

    /**
     * Trims the provided string or returns an empty string if null.
     *
     * @param property form property
     *
     * @return <tt>property.trim()</tt> or "" if <tt>property == null</tt>
     */
    public static String serveProperty(String property) {
        return property == null ? "" : property.trim();
    }

    public static String prepareTSQuery(List<String> queries, String operation) {
        StringJoiner joiner = new StringJoiner(
                operation.equals("or") ? " | " : " & ");

        for (String q : queries) {
            if (q.split("\\s+").length == 1) {
                joiner.add(q);

            } else {
                StringJoiner joiner1 = new StringJoiner(" & ", "(", ")");
                Arrays.stream(q.split("\\s+")).forEach(joiner1::add);
//            for(String s : q.split("\\s+")) {
//                joiner1.add(s);
//            }
                joiner.add(joiner1.toString());
            }
        }

        return joiner.toString();
    }

    public static List<String> prepareResults(List<SearchResult> results) {
//        List<String> headlines = results.stream().map(SearchResult::getHeadline).collect(Collectors.toList());
        List<String> res = new LinkedList<>();
        for (SearchResult r : results) {
            String[] parts = r.getHeadline().split("\n");
//            for (int i = 0; i < parts.length; i++) {
//                parts[i] = "<h" + (i + 3) + ">" + parts[i] + "</h" + (i + 3) + ">";
//            }
            parts[0] += String.format("\t[%.5f]", r.getSimilarity());
            res.add(String.join("<br>", parts));
        }

        return res;
    }

    public static List<String> parseForm(String form) {
        if (form == null) return null;
        List<String> list = new LinkedList<>();

        Matcher m = Pattern.compile("\"([^\"]*)\"|(\\S+)").matcher(form);
        while (m.find()) {
            String arg = m.group(1) != null ?
                    m.group(1) : m.group(2);
            list.add(arg.replaceAll("\"", ""));
        }
        return list;

    }

    public static List<String> getDays(Date start, Date end) {
        Set<Date> days = new LinkedHashSet<>();
        days.add(start);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(start);

        while (calendar.getTime().before(end)) {
            Date result = calendar.getTime();
            days.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        days.add(end);
        return days.stream()
                .map(DATE_FORMAT_DB::format)
                .collect(Collectors.toList());
    }

    public static class PivotResult {
        String query;
        List<Integer> data = new LinkedList<>();

        public PivotResult(String query) {
            this.query = query;
        }

        public String getQuery() {
            return query;
        }

        public List<Integer> getData() {
            return data;
        }
    }
}
