import ivan.vatlin.calculator.Calculator;
import ivan.vatlin.exceptions.DivideByZeroException;
import ivan.vatlin.exceptions.WrongOperandsException;
import org.junit.jupiter.api.Test;

import static ivan.vatlin.calculator.CalculationOperators.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MathematicalOperationsTest {

    private Calculator calculator = new Calculator();

    @Test
    void testAddition() throws WrongOperandsException {
        assertEquals(34, calculator.doOperationOnDoubles(7.54, 26.46, PLUS));
    }

    @Test
    void testSubtraction() throws WrongOperandsException {
        assertEquals(4.64, calculator.doOperationOnDoubles(10.0, 5.36, MINUS));
    }

    @Test
    void testMultiplying() throws WrongOperandsException {
        assertEquals(199.5084, calculator.doOperationOnDoubles(7.54, 26.46, MULTIPLY));
    }

    @Test
    void testDividing() throws WrongOperandsException {
        assertEquals(1.5, calculator.doOperationOnDoubles(3.0, 2.0, DIVIDE));
    }

    @Test
    void testPower() throws WrongOperandsException {
        assertEquals(81, calculator.doOperationOnDoubles(3.0, 4.0, POWER));
    }

    @Test
    void testDividingByZero() {
        assertThrows(DivideByZeroException.class, () -> calculator.doOperationOnDoubles(3.5, 0.0, DIVIDE));
    }
}
