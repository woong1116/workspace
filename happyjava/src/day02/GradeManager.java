package day02;

public class GradeManager {

    public static char convertToGrade(int score) {
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("점수는 0-100 사이여야 합니다");
        }

        if (score >= 90) return 'A';
        else if (score >= 80) return 'B';
        else if (score >= 70) return 'C';
        else if (score >= 60) return 'D';
        else return 'F';
    }

    public static double calculateAverage(int[] scores) {
        if (scores == null || scores.length == 0) {
            throw new IllegalArgumentException("점수 배열이 비어있습니다");
        }

        int sum = 0;
        for (int score : scores) {
            sum += score;
        }

        return Math.round((double)sum / scores.length * 100) / 100.0;
    }
}