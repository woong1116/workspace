package day06;

public class CalculatorTest {
    public static void main(String[] args) {
        Calculator calc = new Calculator();

        // 메소드 호출
        calc.printInfo();
        calc.printNumber(100);

        String version = calc.getVersion();
        System.out.println("버전: " + version);

        int result = calc.add(10, 20);
        System.out.println("10 + 20 = " + result);

        double result2 = calc.calculate(10.5, 3.2, "*");
        System.out.println("10.5 * 3.2 = " + result2);
    }
}