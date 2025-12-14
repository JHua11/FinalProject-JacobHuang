package util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

    @Test
    @DisplayName("lower case input")
    void toTitleCase1() {
        String str = "computer science and math";
        String expected = "Computer Science And Math";
        String result = Util.toTitleCase(str);
        Assertions.assertEquals(expected, result);
    }
    @Test
    @DisplayName("mixed case input")
    void toTitleCase2() {
        String str = "comPuTer SCiENce anD MatH";
        String expected = "Computer Science And Math";
        String result = Util.toTitleCase(str);
        Assertions.assertEquals(expected, result);
    }
    @Test
    @DisplayName("upper case input")
    void toTitleCase3() {
        String str = "COMPUTER SCIENCE AND MATH";
        String expected = "Computer Science And Math";
        String result = Util.toTitleCase(str);
        Assertions.assertEquals(expected, result);
    }
    @Test
    @DisplayName("empty input")
    void toTitleCase4() {
        String str = "";
        String expected = "";
        String result = Util.toTitleCase(str);
        Assertions.assertEquals(expected, result);
    }
    @Test
    @DisplayName("blank input")
    void toTitleCase5() {
        String str = " ";
        String expected = " ";
        String result = Util.toTitleCase(str);
        Assertions.assertEquals(expected, result);
    }
    @Test
    @DisplayName("null input")
    void toTitleCase6() {
        String str = null;
        String expected = null;
        String result = Util.toTitleCase(str);
        Assertions.assertEquals(expected, result);
    }
}