package day07.Exam;

import java.util.Scanner;

public class Exam6 {

    int[] arr;

    public Exam6() {
        arr = new int[5];
    }

    public void inputNum() {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < arr.length; i++) {
            System.out.print((i + 1) + " 번째 정수: ");
            arr[i] = Integer.parseInt(sc.nextLine());
        }
    }

    public void maxMin() {
        int max = arr[0];
        int min = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }

        System.out.println("최대값: " + max);
        System.out.println("최소값: " + min);
    }

    public static void main(String[] args) {
        Exam6 maxmin = new Exam6();
        maxmin.inputNum();
        maxmin.maxMin();
    }
}