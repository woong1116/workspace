package day04;

import java.util.Scanner;

public class ForExam1 {
    public static void main(String[] args) {
        if (args.length > 0) {
            int n = Integer.parseInt(args[0]);


            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.print("입력값: + sc");
                int i = 0;
                int a = Integer.parseInt(args[i]);

                if (a < n) {
                    System.out.println("더 낮습니다!");
                } else if (a > n) {
                    System.out.println("더 높습니다!");
                } else {
                    System.out.println("정답입니다");
                    break;
                }
            }
        }
    }
}
