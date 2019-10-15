package utils;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {
    private Utils utils = new Utils();

    @Test
    void testConcatenateWords() {
        String expectedResult = "oneTwo";
        String result = utils.concatenateWords("one", "Two");
        assertEquals(expectedResult, result);
    }

    @Test
    void testConcatenateWordsNoLatinCharacters() {
        String expectedResult = "МамаМылаРаму";
        String result = utils.concatenateWords("Мама", "Мыла", "Раму");
        assertEquals(expectedResult, result);
    }

    @Test
    void testConcatenateWordsEmptyStrings() {
        String expectedResult = "";
        String result = utils.concatenateWords("", "");
        assertEquals(expectedResult, result);
    }

    @Test
    void testConcatenateWordsNullValue() {
        assertThrows(NullPointerException.class, () -> utils.concatenateWords("one", null));
    }

    @Test
    @Disabled
    void testComputeFactorial() {
        long expectedResult = 720;
        long result = utils.computeFactorial(6);
        assertEquals(expectedResult, result);
    }

    @Test
    void testFactorialOfZero() {
        long expectedResult = 1;
        long result = utils.computeFactorial(0);
        assertEquals(expectedResult, result);
    }

    @Test
    void testFactorialWithTimeout() {
        Random random = new Random();
        assertTimeout(Duration.ofMillis(40), () -> utils.computeFactorial(random.nextInt(1000000)));
    }

    @Test
    void testComputeFactorialExceptionTesting() {
        assertThrows(IllegalArgumentException.class, () -> utils.computeFactorial(-4));
    }
}