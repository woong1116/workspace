package day06;

public class Calculator {
    // 1. 매개변수 X, 반환값 X
    public void printInfo() {
        System.out.println("계산기 v1.0");
    }

    // 2. 매개변수 O, 반환값 X
    public void printNumber(int num) {
        System.out.println("숫자: " + num);
    }

    // 3. 매개변수 X, 반환값 O
    public String getVersion() {
        return "v1.0";
    }

    // 4. 매개변수 O, 반환값 O
    public int add(int a, int b) {
        return a + b;
    }

    // 5. 여러 매개변수
    public double calculate(double x, double y, String operator) {
        switch(operator) {
            case "+": return x + y;
            case "-": return x - y;
            case "*": return x * y;
            case "/": return x / y;
            default: return 0;
        }
    }
}