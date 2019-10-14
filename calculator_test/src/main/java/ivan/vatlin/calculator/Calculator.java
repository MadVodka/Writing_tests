package ivan.vatlin.calculator;

import ivan.vatlin.exceptions.CalculatorValidationException;
import ivan.vatlin.exceptions.DivideByZeroException;
import ivan.vatlin.exceptions.WrongOperandsException;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static ivan.vatlin.calculator.CalculationOperators.*;

public class Calculator {
    public String calculate(String inputExpression) throws WrongOperandsException, CalculatorValidationException {
        String preparedInputExpression = prepareInputExpression(inputExpression);
        String validatedInputExpression = validateInputExpression(preparedInputExpression);

        PolishNotation polishNotation = new PolishNotation(validatedInputExpression, CalculatorRegEx.OPERANDS_REG_EX);
        List<String> reversePolishNotation = polishNotation.getReversePolishNotation();

        Deque<String> numbersStack = new ArrayDeque<>();
        for (String operand : reversePolishNotation) {
            if (Pattern.matches(CalculatorRegEx.NUMBERS_REG_EX, operand)) {
                numbersStack.push(operand);
            } else if (Pattern.matches(CalculatorRegEx.OPERATORS_REG_EX, operand)) {
                Double secondDouble = convertStringToDouble(numbersStack.poll());
                Double firstDouble = convertStringToDouble(numbersStack.poll());

                double result = doOperationOnDoubles(firstDouble, secondDouble, operand);
                numbersStack.push(Double.toString(result));
            } else if (Pattern.matches(CalculatorRegEx.BRACES_REG_EX, operand)) {
                throw new WrongOperandsException("Используются лишние скобки");
            } else {
                throw new WrongOperandsException("Использование других символов запрещено: " + operand);
            }
        }
        return numbersStack.pop();
    }

    private String prepareInputExpression(String inputExpression) {
        return inputExpression.replaceAll("\\s", "");
    }

    public String validateInputExpression(String inputExpression) throws CalculatorValidationException {
        if (inputExpression.equals("")) {
            throw new CalculatorValidationException("Пустое выражение");
        }

        Pattern pattern = Pattern.compile(CalculatorRegEx.RESTRICTED_CASES_REG_EX);
        Matcher matcher = pattern.matcher(inputExpression);

        if (matcher.find()) {
            StringBuilder stringBuilder = new StringBuilder(inputExpression);
            stringBuilder.insert(matcher.start(), " --> ");

            throw new CalculatorValidationException("Ошибка: " + stringBuilder.toString());
        }
        return inputExpression;
    }

    public Double convertStringToDouble(String doubleAsString) throws NumberFormatException {
        return Stream.of(doubleAsString)
//                .filter(Objects::nonNull)
                .mapToDouble(Double::valueOf)
                .findFirst()
                .orElse(0);
    }

    public double doOperationOnDoubles(Double firstDouble, Double secondDouble, String operator)
            throws DivideByZeroException, WrongOperandsException {
        if (firstDouble == null) {
            if (operator.equals(MINUS)) {
                return 0 - secondDouble;
            }
            return secondDouble;
        }

        switch (operator) {
            case POWER:
                return Math.pow(firstDouble, secondDouble);
            case MULTIPLY:
                return firstDouble * secondDouble;
            case DIVIDE:
                if (secondDouble == 0) {
                    throw new DivideByZeroException("Деление на ноль невозможно");
                }
                return firstDouble / secondDouble;
            case PLUS:
                return firstDouble + secondDouble;
            case MINUS:
                return firstDouble - secondDouble;
            default:
                throw new WrongOperandsException("Используются недопустимые операторы");
        }
    }
}
