package nmbp.p1.web;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class UtilTest {

    @Test
    void parseForm1() {
        List<String> r = Util.parseForm("Tarzan");

        assertIterableEquals(r, Collections.singletonList("Tarzan"));
    }

    @Test
    void parseForm2() {
        List<String> r = Util.parseForm("\"Legend of Tarzan\"");

        assertIterableEquals(r, Collections.singletonList("Legend of Tarzan"));
    }

    @Test
    void parseForm3() {
        List<String> r = Util.parseForm("Dancing \"Legend of Tarzan\" \"Lord Of Dance\"");

        assertIterableEquals(r, Arrays.asList("Dancing", "Legend of Tarzan", "Lord Of Dance"));
    }
}