package nmbp.p1.web;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

}
