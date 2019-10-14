package positive;

import ivan.vatlin.calculator.Calculator;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConvertStringNumbersTest {
    private Calculator calculator = new Calculator();

    @Test
    void testConversion() {
        assertEquals(5.21, calculator.convertStringToDouble("5.21"));
    }

    @Test
    void testNullConversion() {
        assertThat(calculator.convertStringToDouble(null), nullValue());
    }
}
