package positive;

import ivan.vatlin.calculator.CalculatorRegEx;
import ivan.vatlin.calculator.PolishNotation;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PolishNotationTest {
    @Test
    void testReversePolishNotation() {
        String mathExpression = "45+2-5:12-(67^2+12:4)*56-100";
        String[] reversedPolishNotation = {"45", "2", "+", "5", "12", ":", "67", "2", "^", "12", "4", ":", "+", "56", "*",
                "100", "-", "-", "-"};
        List<String> expectedResult = Stream.of(reversedPolishNotation).collect(Collectors.toList());
        PolishNotation polishNotation = new PolishNotation(mathExpression, CalculatorRegEx.OPERANDS_REG_EX);
        assertEquals(expectedResult, polishNotation.getReversePolishNotation());
    }
}
