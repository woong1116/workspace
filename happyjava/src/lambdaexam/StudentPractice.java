package lambdaexam;

import java.util.*;

class Student {
    private String name;
    private int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}

public class StudentPractice {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("김철수", 85),
                new Student("이영희", 92),
                new Student("박민수", 78),
                new Student("정수진", 88),
                new Student("최영수", 95)
        );

        // TODO: 1. 80점 이상 학생 이름 출력
        students.stream()
                .filter(student -> student.getScore() >= 80)
                .map(Student::getName)
                .forEach(System.out::println);

        // TODO: 2. 평균 점수 계산
        double average = students.stream()
                .mapToInt(Student::getScore)
                .average()
                .orElse(0);
        System.out.println("\n" + average);

        // TODO: 3. 최고 점수 학생 찾기
        Optional<Student> topStudent = students.stream()
                .max(Comparator.comparingInt(Student::getScore));

        topStudent.ifPresent(s ->
                System.out.println("\n" + s.getName() + " " + s.getScore())
        );
    }
}
