package day10;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.YearMonth;

public class Calendar {

    public static void printCalendar(int year, int month) {

        LocalDate date = LocalDate.of(year, month, 1);
        int start = date.getDayOfWeek().getValue() % 7;
        int last = YearMonth.of(year, month).lengthOfMonth();

        System.out.println("일  월  화  수  목  금  토");

        for (int i = 0; i < start + last; i++) {

            if (i < start) {
                System.out.printf("%3s", "");
            } else {
                System.out.printf("%3d", i - start + 1);
            }

            if ((i + 1) % 7 == 0) {
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("연도 입력: ");
        int year = sc.nextInt();

        System.out.print("월 입력: ");
        int month = sc.nextInt();

        printCalendar(year, month);
    }
}
