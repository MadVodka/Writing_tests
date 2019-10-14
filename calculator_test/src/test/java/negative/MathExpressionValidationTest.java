package negative;

import ivan.vatlin.calculator.Calculator;
import ivan.vatlin.exceptions.CalculatorValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MathExpressionValidationTest {
    private Calculator calculator = new Calculator();

    @Test
    void testEmptyMathExpression() {
        Exception exception = assertThrows(CalculatorValidationException.class,
                () -> calculator.validateInputExpression(""));
        assertThat(exception.getMessage(), equalTo("Пустое выражение"));
    }

    @Test
    void testNullMathExpression() {
        assertThrows(NullPointerException.class, () -> calculator.validateInputExpression(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"*9-10+0.2",
            "7--1",
            "2+()-56",
            "(1-6)(23^2)"})
    void testWrongMathExpression(String expression) {
        Exception exception = assertThrows(CalculatorValidationException.class,
                () -> calculator.validateInputExpression(expression));
        assertThat(exception.getMessage(), startsWith("Ошибка:"));
    }
}
