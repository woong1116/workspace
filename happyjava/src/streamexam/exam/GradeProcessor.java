package streamexam.exam;

import java.util.*;
import java.util.stream.Collectors;

class Student {
    private String name;
    private Map<String, Integer> scores;

    public Student(String name, Map<String, Integer> scores) {
        this.name = name;
        this.scores = scores;
    }

    public double getAverage() {
        return scores.values().stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0);
    }

    public String getName() {
        return name;
    }

    public Map<String, Integer> getScores() {
        return scores;
    }

    public String getGrade() {
        double avg = getAverage();
        if (avg >= 90) return "A";
        else if (avg >= 80) return "B";
        else if (avg >= 70) return "C";
        else return "D";
    }
}

public class GradeProcessor {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("김철수", Map.of("국어", 85, "영어", 90)),
                new Student("이영희", Map.of("국어", 88, "영어", 92)),
                new Student("박민수", Map.of("국어", 80, "영어", 83)),
                new Student("정수진", Map.of("국어", 92, "영어", 81)),
                new Student("최영수", Map.of("국어", 95, "영어", 99))
        );

        System.out.println("\n평균 점수가 80점 이상인 학생");
        students.stream()
                .filter(st -> st.getAverage() >= 80)
                .forEach(st ->
                        System.out.println(st.getName())
                );

        System.out.println("\n과목별 최고 점수");
        Set<String> subjects = students.get(0).getScores().keySet();
        for (String subject : subjects) {
            int maxScore = students.stream()
                    .mapToInt(st -> st.getScores().get(subject))
                    .max()
                    .orElse(0);
            System.out.println(subject + ": " + maxScore);
        }

        System.out.println("\n전체 학생 평균 점수");
        double Average = students.stream()
                .mapToDouble(Student::getAverage)
                .average()
                .orElse(0.0);
        System.out.println(Average);

        System.out.println("\n등급별 학생");
        Map<String, List<Student>> byGrade = students.stream()
                .collect(Collectors.groupingBy(Student::getGrade));

        byGrade.forEach((grade, list) -> {
            System.out.println("등급 " + grade + ": " +
                    list.stream().map(Student::getName).collect(Collectors.joining(", ")));
        });
    }
}