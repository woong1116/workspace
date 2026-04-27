package day03;

public class OpExam {
    public static void main(String[] args) {


        int a = 10, b = 3;
        System.out.println(a + b);  // 13 (덧셈)
        System.out.println(a - b);  // 7  (뺄셈)
        System.out.println(a * b);  // 30 (곱셈)
        System.out.println(a / b);  // 3  (나눗셈 - 정수/정수는 몫)
        System.out.println(a % b);  // 1  (나머지)

        int x = 5, y = 10;
        System.out.println(x == y);  // false (같다)
        System.out.println(x != y);  // true  (다르다)
        System.out.println(x < y);   // true  (작다)
        System.out.println(x > y);   // false (크다)
        System.out.println(x <= y);  // true  (작거나 같다)
        System.out.println(x >= y);  // false (크거나 같다)



        int num = 10;
        num += 5;   // num = num + 5  (15)
        num -= 3;   // num = num - 3  (12)
        num *= 2;   // num = num * 2  (24)
        num /= 4;   // num = num / 4  (6)
        num %= 4;   // num = num % 4  (2)

        int i = 5;
        System.out.println(++i);  // 6 (전위: 먼저 증가 후 사용)
        System.out.println(i++);  // 6 (후위: 먼저 사용 후 증가)
        System.out.println(i);    // 7

        int score = 80;

        // 1. 증감 연산자를 사용하여 score의 값을 1 증가시키고 출력하세요.
        System.out.println(++score);
        // 2. 복합 대입 연산자를 사용하여 증가된 score의 값에 9를 더하고 출력하세요.
        System.out.println(score += 9);
    }
}
