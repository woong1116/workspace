package day07.Exam;

import java.util.Scanner;

public class Exam2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("첫 번째 정수: ");
        int i = Integer.parseInt(sc.nextLine());

        System.out.print("두 번째 정수: ");
        int j = Integer.parseInt(sc.nextLine());

        System.out.print("세 번째 정수: ");
        int k = Integer.parseInt(sc.nextLine());

        int num = i;

        if (j > num) {
            num = j;
        }
        if (k > num) {
            num = k;
        }

        System.out.println("가장 큰 수: " + num);
    }
}