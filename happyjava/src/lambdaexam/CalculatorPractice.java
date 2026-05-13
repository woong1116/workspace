package lambdaexam;

@FunctionalInterface
interface CalFunction {
    int calculate(int a, int b);
}

public class CalculatorPractice {
    public static void main(String[] args) {

        // TODO: 여기에 람다식으로 사칙연산을 구현하세요
        Calculator add = (a, b) -> a + b;
        Calculator subtract = (a, b) -> a - b;
        Calculator multiply = (a, b) -> a * b;
        Calculator divide = (a, b) -> a / b;

        // TODO: calculate 메서드를 완성하세요
        System.out.println("10 + 5 = " + calculate(10, 5, add));
        System.out.println("10 + 5 = " + calculate(10, 5, subtract));
        System.out.println("10 + 5 = " + calculate(10, 5, multiply));
        System.out.println("10 + 5 = " + calculate(10, 5, divide));
    }

    public static double calculate(int a, int b, Calculator calc) {
        // TODO: 구현하세요
        return calc.calculate(a, b);
    }
}