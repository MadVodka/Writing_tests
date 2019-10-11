import ivan.vatlin.calculator.Calculator;
import ivan.vatlin.exceptions.WrongOperandsException;
import org.junit.jupiter.api.Test;

import static ivan.vatlin.calculator.CalculationOperators.MULTIPLY;
import static ivan.vatlin.calculator.CalculationOperators.PLUS;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathematicalOperationsTest {

    private Calculator calculator = new Calculator();

    @Test
    void testAddition() throws WrongOperandsException {
        assertEquals("34.0", calculator.doOperationOnDoubles(7.54, 26.46, PLUS));
    }

    @Test
    void testMultiplying() throws WrongOperandsException {
        assertEquals("199.5084", calculator.doOperationOnDoubles(7.54, 26.46, MULTIPLY));
    }
}
