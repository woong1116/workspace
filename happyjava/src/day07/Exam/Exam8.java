package day07.Exam;

import java.util.Scanner;

public class Exam8 {

    int[] scores;

    public Exam8(int Students) {
        this.scores = new int[Students];
    }

    public void Scores() {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < scores.length; i++) {
            System.out.print((i + 1) + "번째 학생 점수: ");
            scores[i] = Integer.parseInt(sc.nextLine());
        }
    }

    public double average() {
        int sum = 0;
        for (int score : scores) {
            sum += score;
        }
        return (double) sum / scores.length;
    }

    public int maxScore() {
        int max = scores[0];
        for (int score : scores) {
            if (score > max) {
                max = score;
            }
        }
        return max;
    }

    public int above60() {
        int count = 0;
        for (int score : scores) {
            if (score >= 60) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Exam8 st = new Exam8(5);
        st.Scores();

        System.out.println("전체 평균 점수: " + st.average());
        System.out.println("최고 점수: " + st.maxScore());
        System.out.println("60점 이상 학생 수: " + st.above60());
    }
}