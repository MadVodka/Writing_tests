package ivan.vatlin;

import ivan.vatlin.calculator.Calculator;

public class Application {
    public static void main(String[] args) {
        String mathExpression = "-7+(8.67*4):2"; // expected result 10.34
        System.out.println("Выражение: " + mathExpression);

        Calculator calculator = new Calculator();
        try {
            String result = calculator.calculate(mathExpression);
            System.out.println("Результат: " + result);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
