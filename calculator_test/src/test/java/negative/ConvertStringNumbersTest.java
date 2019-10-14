package negative;

import ivan.vatlin.calculator.Calculator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConvertStringNumbersTest {
    private Calculator calculator = new Calculator();

    @ParameterizedTest
    @ValueSource(strings = {"2fg", "NeO,", ",", ".", "0.12,", "!", "3,0", "Вася", ":", "+"})
    void testNonNumberConversion(String stringNumber) {
        assertThrows(NumberFormatException.class, () -> calculator.convertStringToDouble(stringNumber));
    }
}
