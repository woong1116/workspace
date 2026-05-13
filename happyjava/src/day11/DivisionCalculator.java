package day11;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DivisionCalculator {

    public double divide(double a, double b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("0으로 나눌 수 없습니다.");
        } return a / b;
    }

    public static void main(String[] args) {
        DivisionCalculator calc = new DivisionCalculator();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== 나눗셈 계산기 ===");

        try {
            System.out.print("첫 번째 숫자를 입력하세요: ");
            double num1 = scanner.nextDouble();

            System.out.print("두 번째 숫자를 입력하세요: ");
            double num2 = scanner.nextDouble();

            double result = calc.divide(num1, num2);
            System.out.println("결과: " + num1 + " ÷ " + num2 + " = " + result);

        } catch(InputMismatchException ime) {
            System.out.println("오류: 숫자를 입력해주세요.");
        } catch(ArithmeticException amE) {
            System.out.println("오류: " + amE.getMessage());
        }

        scanner.close();

    }
}