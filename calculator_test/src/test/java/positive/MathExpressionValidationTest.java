package positive;

import ivan.vatlin.calculator.Calculator;
import ivan.vatlin.exceptions.CalculatorValidationException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MathExpressionValidationTest {
    private Calculator calculator = new Calculator();

    @ParameterizedTest
    @ValueSource(strings = {"-14+((19^2+36)*10.43):56-0.08",
            "22-67:3+12"})
    void testMathExpression(String expression) throws CalculatorValidationException {
        assertThat(calculator.validateInputExpression(expression), is(expression));
    }
}
