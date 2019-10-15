package negative;

import ivan.vatlin.calculator.Calculator;
import ivan.vatlin.exceptions.DivideByZeroException;
import ivan.vatlin.exceptions.WrongOperandsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static ivan.vatlin.calculator.CalculationOperators.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MathematicalOperationsTest {
    private Calculator calculator = new Calculator();

    @Test
    void testNullSecondNumber() {
        assertThrows(WrongOperandsException.class, () -> calculator.doOperationOnDoubles(8.0, null, MINUS));
    }

    @Test
    void testNullArguments() {
        assertThrows(WrongOperandsException.class, () -> calculator.doOperationOnDoubles(null, null, PLUS));
    }

    @Test
    void testDividingByZero() {
        assertThrows(DivideByZeroException.class, () -> calculator.doOperationOnDoubles(3.5, 0.0,
                DIVIDE));
    }

    @ParameterizedTest
    @ValueSource(strings = {"$", "f", "И", " ", ")", "**", "-("})
    void testUsingWrongOperators(String operator) {
        assertThrows(WrongOperandsException.class, () -> calculator.doOperationOnDoubles(3.5,
                1.1, operator));
    }
}
