package day11;

public class ArrayAccess {

    public static void main(String[] args) {
        int[] numbers = {10, 20, 30, 40, 50};

        try {
            System.out.println("인덱스 0: " + numbers[0]);
            System.out.println("인덱스 3: " + numbers[3]);
            System.out.println("인덱스 10: " + numbers[10]); // 예외 발생!
        } catch (ArrayIndexOutOfBoundsException aie) {
            System.out.println("존재하지 않는 인덱스입니다.");
        }

            System.out.println("프로그램 종료");

    }
}
