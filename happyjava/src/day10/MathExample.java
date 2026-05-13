package day10;

public class MathExample {
    public static void main(String[] args) {
        // 기본 연산
        System.out.println("절대값: " + Math.abs(-10));        // 10
        System.out.println("최대값: " + Math.max(10, 20));     // 20
        System.out.println("최소값: " + Math.min(10, 20));     // 10

        // 제곱과 제곱근
        System.out.println("2의 3제곱: " + Math.pow(2, 3));    // 8.0
        System.out.println("16의 제곱근: " + Math.sqrt(16));   // 4.0
        System.out.println("27의 세제곱근: " + Math.cbrt(27)); // 3.0

        // 반올림, 올림, 내림
        double num = 3.7;
        System.out.println("반올림: " + Math.round(num));      // 4
        System.out.println("올림: " + Math.ceil(num));         // 4.0
        System.out.println("내림: " + Math.floor(num));        // 3.0

        // 삼각함수
        double angle = Math.PI / 4; // 45도
        System.out.println("sin(45°): " + Math.sin(angle));
        System.out.println("cos(45°): " + Math.cos(angle));
        System.out.println("tan(45°): " + Math.tan(angle));

        // 로그
        System.out.println("자연로그: " + Math.log(Math.E));   // 1.0
        System.out.println("상용로그: " + Math.log10(100));    // 2.0

        // 난수 생성
        System.out.println("0~1 난수: " + Math.random());

        // 1~100 사이의 정수 난수
        int randomInt = (int)(Math.random() * 100) + 1;
        System.out.println("1~100 난수: " + randomInt);

        // 상수
        System.out.println("원주율: " + Math.PI);
        System.out.println("자연상수: " + Math.E);
    }
}