package positive;

import ivan.vatlin.calculator.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathExpressionPreparationTest {
    private Calculator calculator = new Calculator();

    @Test
    void testMathExpressionPreparation() {
        assertEquals("32+6-3*78:(5+12)", calculator.prepareInputExpression("32+ 6- \n  3 *  78:( 5+\t12)"));
    }
}
