package nmbp.p1.web;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

/**
 * This class is a utility class used for byte-StringHex conversion in both directions.
 *
 * @author Darian Šarić
 */
public class Util {

    /**
     * Returns a hex number representative of the specified byte array.
     *
     * @param bytes byte array to be converted
     *
     * @return hex number in text
     */
    private static String bytetohex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", 0xFF & b));
        }

        return builder.toString();
    }

    /**
     * Returns a String representation of a SHA-1 digest of the provided password.
     *
     * @param password a password
     *
     * @return SHA-1 digested password
     */
    public static String digestPassword(String password) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("sha-1");
            digest.update(password.getBytes(StandardCharsets.UTF_8));
            return Util.bytetohex(digest.digest());
        } catch (NoSuchAlgorithmException ignored) {
            return "";
        }
    }

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
                StringJoiner joiner1 = new StringJoiner(" & ");
                Arrays.stream(q.split("\\s+")).forEach(joiner1::add);
//            for(String s : q.split("\\s+")) {
//                joiner1.add(s);
//            }
                joiner.add(joiner1.toString());
            }
        }

        return joiner.toString();
    }

}
